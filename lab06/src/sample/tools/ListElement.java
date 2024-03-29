package sample.tools;

import java.time.LocalDate;

public class ListElement {
    private String description;
    private PriorityEnum priority;
    private LocalDate date;
    private String text;
    private int index;

    public ListElement(String description, PriorityEnum priority, LocalDate date, String text, int index) {
        this.description = description;
        this.priority = priority;
        this.date = date;
        this.text = text;
        this.index = index;
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

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
