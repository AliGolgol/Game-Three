package com.agg.challenge.gamethree.game.application.commandservices;

import com.agg.challenge.gamethree.game.domain.GamePlayerRepository;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Game;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Human;
import com.agg.challenge.gamethree.game.domain.command.GameInitCommand;
import com.agg.challenge.gamethree.game.domain.model.GamePlayer;
import com.agg.challenge.gamethree.sharedDomain.event.PlayerCreatedEvent;
import com.agg.challenge.gamethree.sharedDomain.event.PlayerCreatedEventData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PlayerCreatedCommandService {

    @Autowired
    GamePlayerRepository playerRepository;
    @Autowired
    Game gameAggregate;

    /**
     * It create a game throw a event which is raised in Player component
     * @param event is a {@link PlayerCreatedEvent}
     */
    public void createPlayer(PlayerCreatedEvent event) {
        GameInitCommand command = GameInitCommand.builder()
                .player(new Human(event.getEventData().getName(), event.getEventData().getId()))
                .playWith(event.getEventData().getPlayerType()).build();
        gameAggregate.create().init(command);
        playerRepository.add(mapToGamePlayer(event.getEventData()));
        log.info("Game Created Player");
    }

    /**
     * Mapping a {@link PlayerCreatedEventData } to a {@link GamePlayer}
     * @param eventData is a {@link PlayerCreatedEventData}
     * @return a {@link GamePlayer}
     */
    private GamePlayer mapToGamePlayer(PlayerCreatedEventData eventData) {
        return GamePlayer.builder()
                .id(eventData.getId())
                .name(eventData.getName())
                .playerType(eventData.getPlayerType()).build();
    }
}
