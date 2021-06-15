package com.agg.challenge.gamethree.game.application.dtos;

import com.agg.challenge.gamethree.game.domain.model.GamePlayer;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.Status;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GameDTO {
    int addedNumber;
    int number;
    String gameId;
    String message;
    Status status;
    List<GamePlayer> players;
}
