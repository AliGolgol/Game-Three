package com.agg.challenge.gamethree.roundgame.application.outboundservices;

import com.agg.challenge.gamethree.roundgame.domain.GameRound;
import com.agg.challenge.gamethree.roundgame.domain.GameRoundRepository;
import com.agg.challenge.gamethree.sharedDomain.event.GameOverEvent;
import com.agg.challenge.gamethree.sharedDomain.event.GameOverEventData;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RoundGameOverCommandService {

    @Autowired
    GameRoundRepository repository;

    /**
     * Persist an event which is related to Game Over
     * @param event is a {@link GameOverEvent}
     */
    public void gameOver(GameOverEvent event){
        repository.add(mapToGameRound(event.getEventData()));
       log.info("Game Over");
    }

    /**
     * Mapping a {@link GameOverEventData} to a {@link GameRound}
     * @param eventData is a {@link GameOverEventData}
     * @return a {@link GameRound}
     */
    private GameRound mapToGameRound(GameOverEventData eventData){
        return GameRound.builder()
                .gameId(eventData.getGameId())
                .additionNumber(eventData.getAdditionNumber())
                .number(eventData.getNumber())
                .status(String.valueOf(eventData.getStatus()))
                .players(eventData.getPlayers())
                .message(eventData.getMessage()).build();
    }
}
