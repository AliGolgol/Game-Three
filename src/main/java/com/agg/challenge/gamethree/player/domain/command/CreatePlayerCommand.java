package com.agg.challenge.gamethree.player.domain.command;

import com.agg.challenge.gamethree.player.domain.PlayerType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreatePlayerCommand {
    String name;
    PlayerType playerType;
}
