package com.agg.challenge.gamethree.player.domain;

import com.agg.challenge.gamethree.player.application.dtos.PlayerDTO;
import com.agg.challenge.gamethree.player.domain.command.CreatePlayerCommand;
import com.agg.challenge.gamethree.sharedDomain.event.PlayerCreatedEvent;
import com.agg.challenge.gamethree.sharedDomain.event.PlayerCreatedEventData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class PlayerAggregate {
    String id;
    String name;
    PlayerType playerType;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    /**
     * Create a player and raise an event to the Game
     * @param command is a {@link CreatePlayerCommand}
     * @return a {@link PlayerDTO}
     */
    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        this.id = UUID.randomUUID().toString();
        this.name = command.getName();
        this.playerType = command.getPlayerType();

        eventPublisher.publishEvent(new PlayerCreatedEvent(PlayerCreatedEventData.builder()
                .id(id)
                .playerType(playerType)
                .name(name).build()));
        log.info("Event is raised");
        return PlayerDTO.builder().id(id).name(name).playerType(playerType).build();
    }
}
