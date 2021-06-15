package com.agg.challenge.gamethree.sharedDomain.event;

public class GameCreatedEvent {
    GameCreatedEventData eventData;

    public GameCreatedEvent(GameCreatedEventData eventData) {
        this.eventData = eventData;
    }

    public GameCreatedEventData getEventData() {
        return eventData;
    }
}
