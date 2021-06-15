package com.agg.challenge.gamethree.roundgame.application.queryservices;

import com.agg.challenge.gamethree.roundgame.domain.GameRound;
import com.agg.challenge.gamethree.roundgame.domain.GameRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRoundQueryService {

    @Autowired
    GameRoundRepository repository;

    /**
     * Get all Game's event
     * @return list of {@link GameRound}
     */
    public List<GameRound> getAll(){
        return repository.getAll();
    }
}
