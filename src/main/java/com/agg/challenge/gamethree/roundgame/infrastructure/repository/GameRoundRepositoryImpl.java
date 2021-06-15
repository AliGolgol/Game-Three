package com.agg.challenge.gamethree.roundgame.infrastructure.repository;

import com.agg.challenge.gamethree.roundgame.domain.GameRound;
import com.agg.challenge.gamethree.roundgame.domain.GameRoundRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRoundRepositoryImpl implements GameRoundRepository {
    List<GameRound> gameRoundList = new ArrayList<>();

    @Override
    public boolean add(GameRound gameRound) {
        return gameRoundList.add(gameRound);
    }

    @Override
    public Optional<GameRound> getById(String id) {
        return gameRoundList.stream().filter(gameRound -> gameRound.getGameId().equals(id)).findFirst();
    }

    @Override
    public List<GameRound> getAll() {
        return gameRoundList;
    }
}
