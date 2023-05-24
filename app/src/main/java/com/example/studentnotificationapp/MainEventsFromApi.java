package com.example.studentnotificationapp;

import java.util.ArrayList;

public class MainEventsFromApi {
    private String status;
    private String totalResults;
    private ArrayList<ModelClass> events;

    public MainEventsFromApi(String status, String totalResults, ArrayList<ModelClass> events) {
        this.status = status;
        this.totalResults = totalResults;
        this.events = events;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<ModelClass> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<ModelClass> events) {
        this.events = events;
    }
}
