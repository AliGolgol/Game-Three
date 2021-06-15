package com.agg.challenge.gamethree.player.infrastructure.broker;

import com.agg.challenge.gamethree.game.application.commandservices.PlayerCreatedCommandService;
import com.agg.challenge.gamethree.roundgame.application.outboundservices.RoundGameCreatedCommandService;
import com.agg.challenge.gamethree.sharedDomain.event.GameCreatedEvent;
import com.agg.challenge.gamethree.sharedDomain.event.PlayerCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PlayerEventSource {

    @Autowired
    PlayerCreatedCommandService playerCreatedCommandService;
    /**
     * Publish an event to RoundGame components
     * @param event is a {@link GameCreatedEvent}
     */
    public void publishPlayer(PlayerCreatedEvent event){
        playerCreatedCommandService.createPlayer(event);
       log.info("Event is published");
    }
}
