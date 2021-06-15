package com.agg.challenge.gamethree.player.application.outboundservices;

import com.agg.challenge.gamethree.player.infrastructure.broker.PlayerEventSource;
import com.agg.challenge.gamethree.sharedDomain.event.PlayerCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CreatePlayerEventPublisherService {
    @Autowired
    PlayerEventSource eventSource;

    /**
     * It listen to a created player which is raised by Player
     * @param event is a {@link PlayerCreatedEvent}
     */
    @EventListener
    public void handlePlayerCreatedEvent(PlayerCreatedEvent event){
        eventSource.publishPlayer(event);
    }
}
