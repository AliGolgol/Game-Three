package com.agg.challenge.gamethree.game.application.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GameErrorBody {
    private String message;
}
