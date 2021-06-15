package com.agg.challenge.gamethree.game.domain.aggregate.entities;

import com.agg.challenge.gamethree.config.PropertiesConfig;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputGameRound;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import com.agg.challenge.gamethree.player.domain.PlayerType;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Human extends Player {
    private String id;
    private String name;
    static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    static final String DIVIDE_NUMBER = PropertiesConfig.getProperties().getProperty("game.divide");
    InputGameRound inputGameRound;

    public Human(String playerName,String playerId) {
        this.name = playerName;
        id = playerId;
        inputGameRound = null;
    }

    @Override
    public InputNumber play(PlayVisitor playVisitor) {
        return playVisitor.play(this);
    }

    /**
     * Receive the input game round number
     *
     * @param number
     * @return
     */
    @Override
    public void receive(InputGameRound number) {
        this.inputGameRound = number;
    }

    public String getId() {
        return id;
    }

    @Override
    public PlayerType getPlayerType() {
        return null;
    }

    public String getName() {
        return name;
    }
}
