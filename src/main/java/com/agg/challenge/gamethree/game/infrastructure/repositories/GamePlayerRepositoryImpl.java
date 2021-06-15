package com.agg.challenge.gamethree.game.infrastructure.repositories;

import com.agg.challenge.gamethree.game.domain.GamePlayerRepository;
import com.agg.challenge.gamethree.game.domain.model.GamePlayer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GamePlayerRepositoryImpl implements GamePlayerRepository {
    List<GamePlayer> players = new ArrayList<>();

    @Override
    public boolean add(GamePlayer player) {
        return players.add(player);
    }

    @Override
    public List<GamePlayer> getAll() {
        return players;
    }

    @Override
    public Optional<GamePlayer> getById(String id) {
        return players.stream().filter(player->player.getId().equals(id)).findFirst();
    }
}
