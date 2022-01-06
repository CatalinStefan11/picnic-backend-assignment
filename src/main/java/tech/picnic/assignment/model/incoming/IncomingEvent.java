package tech.picnic.assignment.model.incoming;

public class IncomingEvent {
    private String id;
    private String timestamp;
    private IncomingPicker picker;
    private IncomingArticle article;
    private Integer quantity;

    public IncomingEvent() {

    }

    public IncomingEvent(String id, String timestamp, IncomingPicker picker, IncomingArticle article, Integer quantity) {
        this.id = id;
        this.timestamp = timestamp;
        this.picker = picker;
        this.article = article;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public IncomingPicker getPicker() {
        return picker;
    }

    public void setPicker(IncomingPicker picker) {
        this.picker = picker;
    }

    public IncomingArticle getArticle() {
        return article;
    }

    public void setArticle(IncomingArticle article) {
        this.article = article;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
