package com.room303.dutaxi.ui.main.accountfragment.recyclerview;

public class HistoryItem {
    private String departure;
    private String destination;
    private String date;
    private String[] group = new String[3];

    public HistoryItem(String departure, String destination, String date, String[] group) {
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.group = group;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getGroup() {
        return group;
    }

    public void setGroup(String[] group) {
        this.group = group;
    }
}
