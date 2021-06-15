package com.agg.challenge.gamethree.game.domain.aggregate.entities;

import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputGameRound;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import com.agg.challenge.gamethree.player.domain.PlayerType;

public abstract class Player {
    String id;
    String name;
    PlayerType playerType;

    /**
     * Play a Game Round
     * @param playVisitor is a {@link PlayVisitor}
     * @return
     */
    public abstract InputNumber play(PlayVisitor playVisitor);

    /**
     * Receive the input game round number
     * @param number is a {@link InputGameRound}
     * @return
     */
    public abstract void receive(InputGameRound number);
    public abstract String getName();
    public abstract String getId();
    public abstract PlayerType getPlayerType();
}
