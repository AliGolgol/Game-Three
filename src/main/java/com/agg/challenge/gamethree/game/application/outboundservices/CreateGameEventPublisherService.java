package com.agg.challenge.gamethree.game.application.outboundservices;

import com.agg.challenge.gamethree.game.infrastructure.broker.GameEventSource;
import com.agg.challenge.gamethree.sharedDomain.event.GameCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CreateGameEventPublisherService {
    @Autowired
    GameEventSource eventSource;

    /**
     * It listen to a created Game which is raised by GameAggregate component
     * @param event is a {@link GameCreatedEvent}
     */
    @EventListener
    public void handleGameCreatedEvent(GameCreatedEvent event){
        eventSource.publishGame(event);
    }
}
