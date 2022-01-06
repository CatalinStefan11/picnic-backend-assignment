package tech.picnic.assignment.model.incoming;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IncomingArticle {
    private String id;
    private String name;
    private TemperatureZone temperatureZone;

    public IncomingArticle() {

    }

    public IncomingArticle(String id, String name, TemperatureZone temperatureZone) {
        this.id = id;
        this.name = name;
        this.temperatureZone = temperatureZone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemperatureZone getTemperatureZone() {
        return temperatureZone;
    }

    public void setTemperatureZone(TemperatureZone temperatureZone) {
        this.temperatureZone = temperatureZone;
    }

    public enum TemperatureZone {
        @JsonProperty("ambient")
        AMBIENT,
        @JsonProperty("chilled")
        CHILLED;
    }
}
