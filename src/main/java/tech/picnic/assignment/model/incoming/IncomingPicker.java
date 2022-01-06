package tech.picnic.assignment.model.incoming;

import java.util.Objects;

public class IncomingPicker {
    private String id;
    private String name;
    private String activeSince;

    public IncomingPicker() {

    }

    public IncomingPicker(String id, String name, String activeSince) {
        this.id = id;
        this.name = name;
        this.activeSince = activeSince;
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

    public String getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(String activeSince) {
        this.activeSince = activeSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingPicker that = (IncomingPicker) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(activeSince, that.activeSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, activeSince);
    }
}
