package com.shariqansari.vodoassignment.pojo.event_bus;

public class EventBusMovieClick {
    private int position;

    public EventBusMovieClick(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
