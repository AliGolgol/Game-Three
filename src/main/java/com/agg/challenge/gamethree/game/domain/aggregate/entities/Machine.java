package com.agg.challenge.gamethree.game.domain.aggregate.entities;

import com.agg.challenge.gamethree.config.PropertiesConfig;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputGameRound;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import com.agg.challenge.gamethree.player.domain.PlayerType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Machine extends Player {

    static final Map<Integer, Integer> ADDITION_NUMBERS;
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    static final String DIVIDE_NUMBER = PropertiesConfig.getProperties().getProperty("game.divide");
    private String name;
    String id;
    InputGameRound inputGameRound;

    static {
        ADDITION_NUMBERS = new HashMap<>();
        ADDITION_NUMBERS.put(0, 0);
        ADDITION_NUMBERS.put(1, -1);
        ADDITION_NUMBERS.put(2, 1);
    }

    public Machine() {
        this.name = "Machine Player";
        this.id = UUID.randomUUID().toString();
        inputGameRound = null;
    }

    public String getId() {
        return id;
    }

    @Override
    public PlayerType getPlayerType() {
        return null;
    }

    @Override
    public InputNumber play(PlayVisitor playVisitor) {
        return playVisitor.play(this);
    }

    /**
     * Receive a input game round number to play
     *
     * @param number is a {@link InputGameRound}
     * @return
     */
    @Override
    public void receive(InputGameRound number) {
        this.inputGameRound = number;
    }

    public String getName() {
        return name;
    }
}
