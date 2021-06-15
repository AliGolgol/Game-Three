package com.agg.challenge.gamethree.game.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Move {
    String playerId;
    int addedNumber;
    int number;
    String message;
}
