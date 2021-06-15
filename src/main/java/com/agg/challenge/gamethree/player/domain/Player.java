package com.agg.challenge.gamethree.player.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Player {
    String id;
    String name;
    PlayerType playerType;
}
