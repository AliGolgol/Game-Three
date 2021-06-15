package com.agg.challenge.gamethree.sharedDomain.event;

public class PlayerCreatedEvent {
    PlayerCreatedEventData eventData;

    public PlayerCreatedEvent(PlayerCreatedEventData eventData) {
        this.eventData = eventData;
    }

    public PlayerCreatedEventData getEventData() {
        return eventData;
    }
}
