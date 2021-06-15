package com.agg.challenge.gamethree.roundgame.application.outboundservices;

import com.agg.challenge.gamethree.game.domain.model.GamePlayer;
import com.agg.challenge.gamethree.roundgame.domain.GameRound;
import com.agg.challenge.gamethree.roundgame.domain.GameRoundRepository;
import com.agg.challenge.gamethree.roundgame.domain.Player;
import com.agg.challenge.gamethree.sharedDomain.event.GameCreatedEvent;
import com.agg.challenge.gamethree.sharedDomain.event.GameCreatedEventData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RoundGameCreatedCommandService {

    @Autowired
    GameRoundRepository repository;

    /**
     * Persist an event which is related to Created Game
     *
     * @param event is a {@link GameCreatedEvent}
     */
    public void createGame(GameCreatedEvent event) {
        repository.add(mapToGameRound(event.getEventData()));
        log.info("Game Created");
    }

    /**
     * Mapping a {@link GameCreatedEventData} to a {@link GameRound}
     *
     * @param eventData is a {@link GameCreatedEventData}
     * @return a {@link GameRound}
     */
    private GameRound mapToGameRound(GameCreatedEventData eventData) {
        return GameRound.builder()
                .gameId(eventData.getGameId())
                .additionNumber(eventData.getAdditionNumber())
                .number(eventData.getNumber())
                .status(String.valueOf(eventData.getStatus()))
                .players(mapToPlayer(eventData.getPlayers()))
                .message(eventData.getMessage()).build();
    }

    private List<Player> mapToPlayer(List<GamePlayer> players) {
        return players.stream().map(p -> Player.builder()
                .id(p.getId())
                .name(p.getName())
                .playerType(p.getPlayerType()).build()).collect(Collectors.toList());
    }
}
