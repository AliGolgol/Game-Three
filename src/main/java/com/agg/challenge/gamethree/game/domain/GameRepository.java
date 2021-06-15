package com.agg.challenge.gamethree.game.domain;

import com.agg.challenge.gamethree.game.domain.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository {
    boolean add(Game game);
    Optional<Game> getById(String id);
    List<Game> getAll();
}
