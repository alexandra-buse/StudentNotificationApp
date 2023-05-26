package com.example.studentnotificationapp;

public class InteractionsClass {
    private String user, event, liked, recommended, opened, categoryMatched, addedToCalendar;

    public InteractionsClass(String user, String event, String liked, String recommended, String opened, String categoryMatched, String addedToCalendar) {
        this.user = user;
        this.event = event;
        this.liked = liked;
        this.recommended = recommended;
        this.opened = opened;
        this.categoryMatched = categoryMatched;
        this.addedToCalendar = addedToCalendar;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLiked() {
        return liked;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getOpened() {
        return opened;
    }

    public void setOpened(String opened) {
        this.opened = opened;
    }

    public String getCategoryMatched() {
        return categoryMatched;
    }

    public void setCategoryMatched(String categoryMatched) {
        this.categoryMatched = categoryMatched;
    }

    public String getAddedToCalendar() {
        return addedToCalendar;
    }

    public void setAddedToCalendar(String addedToCalendar) {
        this.addedToCalendar = addedToCalendar;
    }
}
