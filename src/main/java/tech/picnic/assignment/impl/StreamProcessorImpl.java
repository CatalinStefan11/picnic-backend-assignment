package tech.picnic.assignment.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import tech.picnic.assignment.api.StreamProcessor;
import tech.picnic.assignment.model.incoming.IncomingArticle;
import tech.picnic.assignment.model.incoming.IncomingEvent;
import tech.picnic.assignment.model.outgoing.OutgoingArticle;
import tech.picnic.assignment.model.outgoing.OutgoingEvent;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamProcessorImpl implements StreamProcessor {
    private static final Logger logger = LoggerFactory.getLogger(StreamProcessorImpl.class);
    private final ObjectMapper objectMapper;
    private final int maxEvents;
    private final Duration maxTime;

    public StreamProcessorImpl(int maxEvents, Duration maxTime) {
        this.maxEvents = maxEvents;
        this.maxTime = maxTime;
        this.objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    @Override
    public void process(InputStream source, OutputStream sink) throws IOException {

        Flux.fromStream(new BufferedReader(
                new InputStreamReader(source)).lines().filter(line -> !StringUtils.isBlank(line)))
                .take(maxEvents)
                .take(maxTime)
                .map(this::parseEvent)
                .filter(event -> IncomingArticle.TemperatureZone.AMBIENT.equals(event.getArticle().getTemperatureZone()))
                .groupBy(IncomingEvent::getPicker)
                .flatMap(Flux::buffer)
                .map(this::convertFromIncomingToOutgoing)
                .sort(Comparator.comparing(event -> Instant.parse(event.getActiveSince())))
                .collectList()
                .subscribe(
                        (outgoingEvents) -> writeListToOutputStream(outgoingEvents, sink),
                        (err) -> logger.info("An error occurred while processing events: {}", err.getMessage()));

    }

    private void writeListToOutputStream(List<OutgoingEvent> eventList, OutputStream sink) {
        try {
            objectMapper.writeValue(sink, eventList);
        } catch (IOException e) {
            logger.info("An error occurred while writing to OutputStream: {}", e.getMessage());
            throw new RuntimeException("Something went wrong while writing to OutputStream!");
        }
    }

    private IncomingEvent parseEvent(String line) {
        try {
            return objectMapper.readValue(line, IncomingEvent.class);
        } catch (IOException e) {
            logger.info("An error occurred while deserializing IncomingEvent: {}", e.getMessage());
            throw new RuntimeException("Something went wrong while parsing IncomingEvent!");
        }
    }

    private OutgoingEvent convertFromIncomingToOutgoing(List<IncomingEvent> incomingEvents) {
        // get the picker from the first element because they have the same picker being grouped by picker earlier
        return new OutgoingEvent(
                incomingEvents.get(0).getPicker().getName(),
                incomingEvents.get(0).getPicker().getActiveSince(),
                incomingEvents.stream()
                        .map(ev -> new OutgoingArticle(ev.getArticle().getName().toUpperCase(), ev.getTimestamp()))
                        .sorted(Comparator.comparing(out -> Instant.parse(out.getTimestamp())))
                        .collect(Collectors.toList()));

    }
}
