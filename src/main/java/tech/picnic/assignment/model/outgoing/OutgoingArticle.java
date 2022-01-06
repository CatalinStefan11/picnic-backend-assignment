package tech.picnic.assignment.model.outgoing;

import java.io.Serializable;


public class OutgoingArticle implements Serializable {
    private String articleName;
    private String timestamp;

    public OutgoingArticle() {

    }

    public OutgoingArticle(String articleName, String timestamp) {
        this.articleName = articleName;
        this.timestamp = timestamp;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
