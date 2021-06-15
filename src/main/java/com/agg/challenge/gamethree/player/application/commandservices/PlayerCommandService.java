package com.agg.challenge.gamethree.player.application.commandservices;

import com.agg.challenge.gamethree.player.application.dtos.PlayerDTO;
import com.agg.challenge.gamethree.player.domain.Player;
import com.agg.challenge.gamethree.player.domain.PlayerAggregate;
import com.agg.challenge.gamethree.player.domain.PlayerRepository;
import com.agg.challenge.gamethree.player.domain.command.CreatePlayerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCommandService {
    @Autowired
    private PlayerAggregate playerAggregate;
    @Autowired
    private PlayerRepository repository;

    /**
     * Create a player
     * @param command is a {@link CreatePlayerCommand}
     * @return a {@link PlayerDTO}
     */
    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        PlayerDTO player = playerAggregate.createPlayer(command);
        repository.add(new Player(player.getId(),player.getName(),player.getPlayerType()));
        return player;
    }
}
