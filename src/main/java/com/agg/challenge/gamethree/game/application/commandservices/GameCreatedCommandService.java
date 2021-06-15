package com.agg.challenge.gamethree.game.application.commandservices;

import com.agg.challenge.gamethree.game.application.dtos.GameDTO;
import com.agg.challenge.gamethree.game.domain.GameRepository;
import com.agg.challenge.gamethree.game.domain.command.GameInitCommand;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Game;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GameCreatedCommandService {
    @Autowired
    Game gameAggregate;

    @Autowired
    GameRepository gameRepository;

    /**
     * Play a game round
     * @param inputNumber is a {@link InputNumber}
     * @param playerId is a {@link String}
     * @return is a {@link GameDTO}
     */
    public GameDTO play(InputNumber inputNumber, String playerId) {
        GameDTO aggregate = gameAggregate.play(inputNumber, playerId);
        gameRepository.add(mapToGame(aggregate));
        return aggregate;
    }

    /**
     * Initialize game and generate a random number to start a game
     * @param command is a {@link GameInitCommand}
     * @return is a {@link GameDTO}
     */
    public GameDTO initGame(GameInitCommand command) {
        GameDTO aggregate = gameAggregate.create().init(command);
        gameRepository.add(mapToGame(aggregate));
        return aggregate;
    }

    /**
     * It maps a {@link GameDTO} to a {@link Game} model
     * @param dto is a {@link GameDTO}
     * @return a {@link Game} model
     */
    private com.agg.challenge.gamethree.game.domain.model.Game mapToGame(GameDTO dto){
        return com.agg.challenge.gamethree.game.domain.model.Game.builder()
                .id(dto.getGameId())
                .players(dto.getPlayers())
                .status(dto.getStatus())
                .build();
    }
}
