package com.agg.challenge.gamethree.sharedDomain.event;

import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.Status;
import com.agg.challenge.gamethree.game.domain.model.GamePlayer;
import com.agg.challenge.gamethree.roundgame.domain.Player;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GameCreatedEventData {
    int additionNumber;
    int number;
    String gameId;
    String message;
    Status status;
    List<GamePlayer> players;
}
