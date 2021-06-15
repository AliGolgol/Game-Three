package com.agg.challenge.gamethree.game.domain.aggregate.entities;

import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;

public class PlayVisitorImpl implements PlayVisitor {

    @Override
    public InputNumber play(Human player) {
        int result = player.inputGameRound.sum() / Integer.parseInt(player.DIVIDE_NUMBER);
        return new InputNumber(player.inputGameRound.getAddedNumber(), result);
    }

    @Override
    public InputNumber play(Machine player) {
        int remain = player.inputGameRound.getNumber() % Integer.parseInt(player.DIVIDE_NUMBER);
        int addedNumber = player.ADDITION_NUMBERS.get(remain);
        int result = (player.inputGameRound.getNumber() + addedNumber) / Integer.parseInt(player.DIVIDE_NUMBER);
        return new InputNumber(addedNumber, result);
    }
}
