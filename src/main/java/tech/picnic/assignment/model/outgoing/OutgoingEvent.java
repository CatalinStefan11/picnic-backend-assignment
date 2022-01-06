package tech.picnic.assignment.model.outgoing;

import java.io.Serializable;
import java.util.List;


public class OutgoingEvent implements Serializable {
    private String pickerName;
    private String activeSince;
    private List<OutgoingArticle> picks;

    public OutgoingEvent() {

    }

    public OutgoingEvent(String pickerName, String activeSince, List<OutgoingArticle> picks) {
        this.pickerName = pickerName;
        this.activeSince = activeSince;
        this.picks = picks;
    }

    public String getPickerName() {
        return pickerName;
    }

    public void setPickerName(String pickerName) {
        this.pickerName = pickerName;
    }

    public String getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(String activeSince) {
        this.activeSince = activeSince;
    }

    public List<OutgoingArticle> getPicks() {
        return picks;
    }

    public void setPicks(List<OutgoingArticle> picks) {
        this.picks = picks;
    }

}
