package com.agg.challenge.gamethree.player.infrastructure.repositories;

import com.agg.challenge.gamethree.player.domain.Player;
import com.agg.challenge.gamethree.player.domain.PlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {
    List<Player> players = new ArrayList<>();

    @Override
    public boolean add(Player player) {
        return players.add(player);
    }

    @Override
    public List<Player> getAll() {
        return players;
    }

    @Override
    public Optional<Player> getById(String id) {
        return players.stream().filter(player->player.getId().equals(id)).findFirst();
    }
}
