package com.agg.challenge.gamethree.game.domain.model;

import com.agg.challenge.gamethree.player.domain.PlayerType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GamePlayer {
    String id;
    String name;
    PlayerType playerType;
}
