package com.agg.challenge.gamethree.player.domain;

import com.agg.challenge.gamethree.player.application.dtos.PlayerDTO;
import com.agg.challenge.gamethree.player.domain.command.CreatePlayerCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(value={SpringExtension.class})
@SpringBootTest
class PlayerAggregateTest {
    @Autowired
    PlayerAggregate playerAggregate;

    @Test
    public void should_create_the_player(){
        PlayerDTO alice = playerAggregate.createPlayer(CreatePlayerCommand.builder()
                .playerType(PlayerType.Human)
                .name("Alice").build());

        assertEquals("Alice",alice.getName());
    }
}