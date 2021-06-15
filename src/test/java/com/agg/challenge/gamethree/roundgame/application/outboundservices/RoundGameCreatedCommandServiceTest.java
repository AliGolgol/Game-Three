package com.agg.challenge.gamethree.roundgame.application.outboundservices;

import com.agg.challenge.gamethree.player.domain.PlayerType;
import com.agg.challenge.gamethree.roundgame.domain.GameRound;
import com.agg.challenge.gamethree.roundgame.domain.GameRoundRepository;
import com.agg.challenge.gamethree.roundgame.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(value={SpringExtension.class})
@SpringBootTest
class RoundGameCreatedCommandServiceTest {

    @Autowired
    RoundGameCreatedCommandService commandService;

    @MockBean
    GameRoundRepository repository;

    @Test
    public void should_persist_event(){
        GameRound gameRound = GameRound.builder()
                .gameId(UUID.randomUUID().toString())
                .message("Game Created")
                .number(56)
                .additionNumber(1)
                .players(Arrays.asList(Player.builder().id(UUID.randomUUID().toString()).name("Ali").playerType(PlayerType.Human).build())).build();

        Mockito.when(repository.add(gameRound)).thenReturn(true);
        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(gameRound));

        assertEquals(1,repository.getAll().size());
    }
}