package com.agg.challenge.gamethree.game.domain;

import com.agg.challenge.gamethree.game.domain.model.GamePlayer;

import java.util.List;
import java.util.Optional;

public interface GamePlayerRepository {
    boolean add(GamePlayer player);
    List<GamePlayer> getAll();
    Optional<GamePlayer> getById(String id);
}
