package com.agg.challenge.gamethree.player.domain;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    boolean add(Player player);
    List<Player> getAll();
    Optional<Player> getById(String id);
}
