package com.agg.challenge.gamethree.game.domain.aggregate.entities;

import com.agg.challenge.gamethree.config.PropertiesConfig;
import com.agg.challenge.gamethree.game.application.dtos.GameDTO;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.GameState;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputGameRound;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import com.agg.challenge.gamethree.game.domain.command.GameInitCommand;
import com.agg.challenge.gamethree.game.domain.exception.GameRoundException;
import com.agg.challenge.gamethree.game.domain.model.GamePlayer;
import com.agg.challenge.gamethree.game.domain.winLogic.Winner;
import com.agg.challenge.gamethree.game.domain.winLogic.WinnerImp;
import com.agg.challenge.gamethree.sharedDomain.event.GameCreatedEvent;
import com.agg.challenge.gamethree.sharedDomain.event.GameCreatedEventData;
import com.agg.challenge.gamethree.sharedDomain.event.GameOverEvent;
import com.agg.challenge.gamethree.sharedDomain.event.GameOverEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.agg.challenge.gamethree.game.domain.aggregate.valueobject.Status.*;
import static com.agg.challenge.gamethree.player.domain.PlayerType.*;

@Service
public class Game {
    String id;
    GameState state;
    static List<Player> players = new ArrayList<>();
    int currentPlayer = 0;
    Winner winnerLogic;
    String MAX_RANGE;
    String MIN_RANGE;
    int startNumber;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    /**
     * Create a Game with a Created state
     *
     * @return a {@link Game} as an Aggregate
     */
    public Game create() {
        state = new GameState(CREATED);
        this.id = getId();
        winnerLogic = new WinnerImp();
        MAX_RANGE = PropertiesConfig.getProperties().getProperty("game.max-range");
        MIN_RANGE = PropertiesConfig.getProperties().getProperty("game.min-range");
        raiseGameCreatedEvent(startNumber, 0, state, "Game is Created!");
        return this;
    }

    /**
     * Initialize a Game and generate a random number to start
     *
     * @param command is a {@link GameInitCommand}
     * @return is a {@link GameDTO}
     */
    public GameDTO init(GameInitCommand command) {
        players.add(command.getPlayer());
        generateStartNumber();

        if (command.getPlayWith().equals(Machine)) {
            players.add(new Machine());
            state = new GameState(STARTED);
            raiseGameCreatedEvent(startNumber, 0, state, "Game is Started!");
            return mapToGameDto(startNumber, 0, state, "Game Started!");
        }
        return players.size() > 1 ? mapToGameDto(startNumber, 0, new GameState(STARTED), "Game Started!")
                : mapToGameDto(startNumber, 0, new GameState(WAITING), "Please Waite to peer!");
    }

    /**
     * Play a Game Round
     *
     * @param inputNumber is a {@link InputNumber}
     * @param playerId    is a {@link String}
     * @return a {@link GameDTO}
     */
    public GameDTO play(InputNumber inputNumber, String playerId) {
        if (isPlayerTurn(playerId)) {
            InputGameRound inputGameRound = new InputGameRound(inputNumber.getAddedNumber(), inputNumber.getNumber());
            inputGameRound.validate();
            Player player = getPlayer(playerId).get();
            player.receive(inputGameRound);
            InputNumber result = player.play(new PlayVisitorImpl());

            if (winnerLogic.apply(result.getNumber())) {
                state = new GameState(GAME_OVER);
                raiseGameOverEvent(result.getNumber(), result.getAddedNumber(), new GameState(GAME_OVER),
                        String.format("The winner is %s!", getPlayer(playerId).get().getName()));
                return mapToGameDto(result.getNumber(), result.getAddedNumber(), new GameState(GAME_OVER),
                        String.format("The winner is %s!", getPlayer(playerId).get().getName()));
            }
            nextPlayer();

            if (players.get(currentPlayer) instanceof Machine) {
                player = new Machine();
                player.receive(new InputGameRound(result.getAddedNumber(), result.getNumber()));
                result = player.play(new PlayVisitorImpl());
                if (winnerLogic.apply(result.getNumber())) {
                    state = new GameState(GAME_OVER);
                    raiseGameOverEvent(result.getNumber(), result.getAddedNumber(), new GameState(GAME_OVER), String.format("The winner is Machine Player!"));
                    return mapToGameDto(result.getNumber(), result.getAddedNumber(), new GameState(GAME_OVER), String.format("The winner is Machine Player!"));
                }
                nextPlayer();
            }
            return mapToGameDto(result.getNumber(), result.getAddedNumber(), new GameState(GAME_OVER),
                    String.format("%s plays %s", getPlayer(playerId).get().getName(), result.getNumber()));
        } else {
            throw new GameRoundException("It is not your turn");
        }
    }

    /**
     * Generate a UUID as a Id
     *
     * @return a {@link String}
     */
    private String getId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Raise an event when a game is created
     *
     * @param number   is a {@link Integer}
     * @param addedNum is a {@link Integer}
     * @param state    is a {@link GameState}
     * @param message  is a {@link String}
     */
    private void raiseGameCreatedEvent(int number, int addedNum, GameState state, String message) {
        eventPublisher.publishEvent(new GameCreatedEvent(GameCreatedEventData.builder()
                .gameId(id)
                .number(number)
                .additionNumber(addedNum)
                .status(state.getStatus())
                .players(players.stream().map(x-> mapToPlayer(x)).collect(Collectors.toList()))
                .message(message).build()));
    }

    private GamePlayer mapToPlayer(Player player) {
        return GamePlayer.builder()
                .id(player.getId())
                .name(player.getName())
                .playerType(player.getPlayerType()).build();
    }

    /**
     * Raise an event when a game is over
     *
     * @param number   is a {@link Integer}
     * @param addedNum is a {@link Integer}
     * @param state    is a {@link GameState}
     * @param message  is a {@link String}
     */
    private void raiseGameOverEvent(int number, int addedNum, GameState state, String message) {
        eventPublisher.publishEvent(new GameOverEvent(GameOverEventData.builder()
                .gameId(id)
                .number(number)
                .additionNumber(addedNum)
                .status(state.getStatus())
                .message(message).build()));
    }

    /**
     * Mapping to a {@link GameDTO}
     *
     * @param number   is a {@link Integer}
     * @param addedNum is a {@link Integer}
     * @param state    is a {@link GameState}
     * @param message  is a {@link String}
     * @return a {@link GameDTO}
     */
    private GameDTO mapToGameDto(int number, int addedNum, GameState state, String message) {
        return GameDTO.builder()
                .gameId(id)
                .number(number)
                .addedNumber(addedNum)
                .status(state.getStatus())
                .players(players.stream().map(p -> GamePlayer.builder()
                        .id(p.id)
                        .name(p.name)
                        .playerType(p.playerType).build()).collect(Collectors.toList()))
                .message(message).build();
    }

    /**
     * Set the next player's turn to play
     */
    private void nextPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     * Get a player by Id
     * If there is not a Player it throw a {@link GameRoundException}
     * @param playerId is a {@link String}
     * @return a {@link Player}
     */
    private Optional<Player> getPlayer(String playerId) {
        try {
            return players.stream().filter(x -> x.getId().equals(playerId)).findFirst();
        } catch (Exception e) {
            throw new GameRoundException("There is not a Player");
        }
    }

    /**
     * It is a circle array to determine player's turn
     * @param playerId is a {@link String}
     * @return a {@link Boolean}
     */
    private boolean isPlayerTurn(String playerId) {
        if (players.get(currentPlayer).getId().equals(playerId)) {
            return true;
        }
        return false;
    }

    /**
     * Generate a random number to start a game
     */
    private void generateStartNumber() {
        startNumber = ThreadLocalRandom.current().nextInt(Integer.parseInt(MIN_RANGE), Integer.parseInt(MAX_RANGE));
    }
}
