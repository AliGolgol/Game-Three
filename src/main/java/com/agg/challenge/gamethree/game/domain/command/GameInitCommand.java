package com.agg.challenge.gamethree.game.domain.command;

import com.agg.challenge.gamethree.game.domain.aggregate.entities.Player;
import com.agg.challenge.gamethree.player.domain.PlayerType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameInitCommand {
    Player player;
    PlayerType playWith;
}
