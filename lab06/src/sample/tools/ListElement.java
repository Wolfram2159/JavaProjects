package sample.tools;

import java.util.Date;

public class ListElement {
    private String description;
    private String priority;
    private Date date;
    private String text;

    public ListElement(String description, String priority, Date date, String text) {
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.text = text;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
