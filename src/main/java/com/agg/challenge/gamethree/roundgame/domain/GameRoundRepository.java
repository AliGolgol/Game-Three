package com.agg.challenge.gamethree.roundgame.domain;

import java.util.List;
import java.util.Optional;

public interface GameRoundRepository {
    boolean add(GameRound gameRound);
    Optional<GameRound> getById(String id);
    List<GameRound> getAll();
}
