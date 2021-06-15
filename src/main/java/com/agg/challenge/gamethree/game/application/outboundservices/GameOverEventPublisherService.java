package com.agg.challenge.gamethree.game.application.outboundservices;

import com.agg.challenge.gamethree.game.infrastructure.broker.GameEventSource;
import com.agg.challenge.gamethree.sharedDomain.event.GameOverEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class GameOverEventPublisherService {
    @Autowired
    GameEventSource eventSource;

    /**
     * It listen to a game over which is raised by Game component
     * @param event is a {@link GameOverEvent}
     */
    @EventListener
    public void handleGameOverEvent(GameOverEvent event){
        eventSource.publishGameOver(event);
    }
}
