package com.agg.challenge.gamethree.roundgame.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GameRound {
    int additionNumber;
    int number;
    String gameId;
    String message;
    String status;
    List<Player> players;
}
