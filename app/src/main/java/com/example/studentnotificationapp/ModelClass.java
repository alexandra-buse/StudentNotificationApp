package com.example.studentnotificationapp;

public class ModelClass {
    private String eventId, title, description, category, date, hour;

    public ModelClass(String eventId, String title, String description, String category, String date, String hour) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.hour = hour;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
