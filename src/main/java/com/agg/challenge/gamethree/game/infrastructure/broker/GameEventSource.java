package com.agg.challenge.gamethree.game.infrastructure.broker;

import com.agg.challenge.gamethree.roundgame.application.outboundservices.RoundGameCreatedCommandService;
import com.agg.challenge.gamethree.roundgame.application.outboundservices.RoundGameOverCommandService;
import com.agg.challenge.gamethree.sharedDomain.event.GameCreatedEvent;
import com.agg.challenge.gamethree.sharedDomain.event.GameOverEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameEventSource {

    @Autowired
    RoundGameCreatedCommandService roundGameService;
    @Autowired
    RoundGameOverCommandService gameOverCommandService;
    /**
     * Publish an event to RoundGame components
     * @param event is a {@link GameCreatedEvent}
     */
    public void publishGame(GameCreatedEvent event){
        roundGameService.createGame(event);
    }

    /**
     * Publish an event to RoundGame components
     * @param event is a {@link GameCreatedEvent}
     */
    public void publishGameOver(GameOverEvent event){
        gameOverCommandService.gameOver(event);
    }
}
