package com.agg.challenge.gamethree.player.interfaces.restapi;

import com.agg.challenge.gamethree.player.application.commandservices.PlayerCommandService;
import com.agg.challenge.gamethree.player.application.dtos.PlayerDTO;
import com.agg.challenge.gamethree.player.domain.command.CreatePlayerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    @Autowired
    PlayerCommandService playerCommandService;

    /**
     * Play with peer that can be a Human or Machine
     *
     * @param command is a {@link CreatePlayerCommand}
     * @return a {@link PlayerDTO} includes player's name, player type
     */
    @PostMapping(value = "/create")
    public PlayerDTO create(@RequestBody CreatePlayerCommand command) {
        PlayerDTO player = playerCommandService.createPlayer(command);
        return player;
    }
}
