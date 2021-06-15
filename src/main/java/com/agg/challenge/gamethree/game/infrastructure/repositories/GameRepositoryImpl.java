package com.agg.challenge.gamethree.game.infrastructure.repositories;

import com.agg.challenge.gamethree.game.domain.GameRepository;
import com.agg.challenge.gamethree.game.domain.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GameRepositoryImpl implements GameRepository {
    List<Game> games = new ArrayList<>();

    @Override
    public boolean add(Game game){
        return games.add(game);
    }

    @Override
    public Optional<Game> getById(String id) {
        return games.stream().filter(game -> game.getId().equals(id)).findFirst();
    }

    @Override
    public List<Game> getAll() {
        return null;
    }
}
