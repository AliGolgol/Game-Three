package com.agg.challenge.gamethree.game.application.commandservices;

import com.agg.challenge.gamethree.config.PropertiesConfig;
import com.agg.challenge.gamethree.game.application.dtos.GameDTO;
import com.agg.challenge.gamethree.game.domain.GameRepository;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Human;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Player;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.Status;
import com.agg.challenge.gamethree.game.domain.command.GameInitCommand;
import com.agg.challenge.gamethree.game.domain.exception.GameRoundException;
import com.agg.challenge.gamethree.game.domain.model.Game;
import com.agg.challenge.gamethree.player.domain.PlayerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@ExtendWith(value={SpringExtension.class})
@SpringBootTest
class GameCreatedCommandServiceTest {

    @Autowired
    GameCreatedCommandService gameCreatedCommandService;

    @MockBean
    GameRepository repository;

    static {
        PropertiesConfig.initialize("application.properties");
    }

    @Test
    public void should_return_game_state_is_started(){
        Player player = new Human("Ali",UUID.randomUUID().toString());
        GameInitCommand command = GameInitCommand.builder().player(player).playWith(PlayerType.Machine).build();

        Game game = Game.builder().build();
        Mockito.when(repository.add(game)).thenReturn(true);
        GameDTO dto = gameCreatedCommandService.initGame(command);

        assertEquals(Status.STARTED,dto.getStatus());
    }

    @Test
    public void should_return_game_state_is_waiting(){
        Player player = new Human("Ali",UUID.randomUUID().toString());
        GameInitCommand command = GameInitCommand.builder().player(player).playWith(PlayerType.Human).build();

        Game game = Game.builder().build();
        Mockito.when(repository.add(game)).thenReturn(true);
        GameDTO dto = gameCreatedCommandService.initGame(command);

        assertEquals(Status.WAITING,dto.getStatus());
    }

    @Test
    public void should_return_gameRoundException(){
        Player player = new Human("Ali",UUID.randomUUID().toString());
        GameInitCommand command = GameInitCommand.builder().player(player).playWith(PlayerType.Human).build();

        Game game = Game.builder().build();
        Mockito.when(repository.add(game)).thenReturn(true);
        GameDTO result1= gameCreatedCommandService.initGame(command);

        assertThrows(GameRoundException.class,()->
                gameCreatedCommandService.play(new InputNumber(1,result1.getNumber()),UUID.randomUUID().toString()));
    }
}