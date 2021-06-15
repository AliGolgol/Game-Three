package com.agg.challenge.gamethree.player.application.commandservices;

import com.agg.challenge.gamethree.player.application.dtos.PlayerDTO;
import com.agg.challenge.gamethree.player.domain.Player;
import com.agg.challenge.gamethree.player.domain.PlayerRepository;
import com.agg.challenge.gamethree.player.domain.PlayerType;
import com.agg.challenge.gamethree.player.domain.command.CreatePlayerCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(value={SpringExtension.class})
@SpringBootTest
class PlayerCommandServiceTest {

    @Autowired
    PlayerCommandService playerCommandService;
    @MockBean
    PlayerRepository playerRepository;

    @Test
    public void should_persist_the_player(){
        CreatePlayerCommand command = CreatePlayerCommand.builder().name("Ali").playerType(PlayerType.Human).build();
        PlayerDTO player = playerCommandService.createPlayer(command);
        Mockito.when(playerRepository.getAll())
                .thenReturn(Arrays.asList(new Player(player.getId(),player.getName(),player.getPlayerType())));

        assertEquals(1,playerRepository.getAll().size());
    }
}