package com.example.studentnotificationapp;

public class InteractionsClass {
    private String user, event;
    private Integer liked, recommended, opened, categoryMatched, addedToCalendar;

    public InteractionsClass(String user, String event, Integer liked, Integer recommended, Integer opened, Integer categoryMatched, Integer addedToCalendar) {
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

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Integer getRecommended() {
        return recommended;
    }

    public void setRecommended(Integer recommended) {
        this.recommended = recommended;
    }

    public Integer getOpened() {
        return opened;
    }

    public void setOpened(Integer opened) {
        this.opened = opened;
    }

    public Integer getCategoryMatched() {
        return categoryMatched;
    }

    public void setCategoryMatched(Integer categoryMatched) {
        this.categoryMatched = categoryMatched;
    }

    public Integer getAddedToCalendar() {
        return addedToCalendar;
    }

    public void setAddedToCalendar(Integer addedToCalendar) {
        this.addedToCalendar = addedToCalendar;
    }
}