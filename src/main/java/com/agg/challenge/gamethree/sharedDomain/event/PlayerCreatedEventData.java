package com.agg.challenge.gamethree.sharedDomain.event;

import com.agg.challenge.gamethree.player.domain.PlayerType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlayerCreatedEventData {
    String id;
    String name;
    PlayerType playerType;
}
