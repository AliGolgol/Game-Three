package com.agg.challenge.gamethree.roundgame.domain;

import com.agg.challenge.gamethree.player.domain.PlayerType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Player {
    String id;
    String name;
    PlayerType playerType;
}
