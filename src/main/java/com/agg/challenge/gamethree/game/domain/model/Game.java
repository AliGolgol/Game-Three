package com.agg.challenge.gamethree.game.domain.model;

import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.Status;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Game {
    String id;
    List<GamePlayer> players;
    Status status;
}
