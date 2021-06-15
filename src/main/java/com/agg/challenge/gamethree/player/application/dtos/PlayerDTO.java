package com.agg.challenge.gamethree.player.application.dtos;

import com.agg.challenge.gamethree.player.domain.PlayerType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerDTO {
    String id;
    String name;
    PlayerType playerType;
}
