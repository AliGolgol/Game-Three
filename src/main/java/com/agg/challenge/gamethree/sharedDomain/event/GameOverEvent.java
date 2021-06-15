package com.agg.challenge.gamethree.sharedDomain.event;

public class GameOverEvent {
    GameOverEventData eventData;

    public GameOverEvent(GameOverEventData eventData) {
        this.eventData = eventData;
    }

    public GameOverEventData getEventData() {
        return eventData;
    }
}
