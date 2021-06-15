package com.agg.challenge.gamethree.game.domain.aggregate.entities;

import com.agg.challenge.gamethree.config.PropertiesConfig;
import com.agg.challenge.gamethree.game.application.dtos.GameDTO;
import com.agg.challenge.gamethree.game.domain.command.GameInitCommand;
import com.agg.challenge.gamethree.game.domain.exception.GameRoundException;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.GameState;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import com.agg.challenge.gamethree.player.domain.PlayerType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static com.agg.challenge.gamethree.game.domain.aggregate.valueobject.Status.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestPropertySource("/application.properties")
@ExtendWith(value={SpringExtension.class})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
@SpringBootTest
class GameTest {

    @Autowired
    Game game;

    static {
        PropertiesConfig.initialize("application.properties");
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_return_created_status() {
        game.create();

        assertEquals(new GameState(CREATED), game.state);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_return_playing_state() {
        GameInitCommand command = GameInitCommand.builder()
                .playWith(PlayerType.Machine)
                .player(new Human("Ali", UUID.randomUUID().toString())).build();
        game.create().init(command);

        assertEquals(new GameState(STARTED), game.state);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_throw_game_exception_when_it_is_not_your_turn() {
        GameInitCommand command = GameInitCommand.builder()
                .playWith(PlayerType.Machine)
                .player(new Human("Ali",UUID.randomUUID().toString())).build();
        game.create().init(command);
        String playerId = game.players.get(1).getId();

        assertThrows(GameRoundException.class, () -> {
            game.play(new InputNumber(12, 0), playerId);
        });
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_return_first_one_as_a_winner() {
        Player player = new Human("Ali",UUID.randomUUID().toString());
        GameInitCommand command = GameInitCommand.builder()
                .playWith(PlayerType.Machine)
                .player(player).build();
        game.create().init(command);
        String playerId = game.players.get(0).getId();
        InputNumber inputNumber = new InputNumber(-1, 4);
        GameDTO play = game.play(inputNumber, playerId);
        String message = String.format("The winner is %s!", game.players.get(0).getName());

        assertEquals(message, play.getMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_return_machine_as_a_winner() {
        Player player = new Human("Ali",UUID.randomUUID().toString());
        GameInitCommand command = GameInitCommand.builder()
                .playWith(PlayerType.Machine)
                .player(player).build();
        game.create().init(command);
        String playerId1 = game.players.get(0).getId();
        InputNumber inputNumber = new InputNumber(1, game.startNumber);

        GameDTO play1 = game.play(inputNumber, playerId1);
        GameDTO play2 = game.play(new InputNumber(0,play1.getNumber()), playerId1);

        String message = String.format("The winner is %s!", game.players.get(1).getName());
        assertEquals(message, play2.getMessage());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_return_second_one_as_a_winner() {
        Player player1 = new Human("Alice",UUID.randomUUID().toString());
        Player player2 = new Human("Ali",UUID.randomUUID().toString());

        GameInitCommand command1 = GameInitCommand.builder()
                .playWith(PlayerType.Human)
                .player(player1).build();
        game.create().init(command1);

        GameInitCommand command2 = GameInitCommand.builder()
                .playWith(PlayerType.Human)
                .player(player2).build();
        game.init(command2);

        String playerId1 = game.players.get(0).getId();
        String playerId2 = game.players.get(1).getId();

        GameDTO play1 = game.play(new InputNumber(1,game.startNumber), playerId1);
        GameDTO play2 = game.play(new InputNumber(-1,play1.getNumber()), playerId2);
        play1 = game.play(new InputNumber(0,play2.getNumber()), playerId1);
        play2 = game.play(new InputNumber(1,play1.getNumber()), playerId2);
        String message = String.format("The winner is %s!", game.players.get(1).getName());

        assertEquals(message, play2.getMessage());
    }
}