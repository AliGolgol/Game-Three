package com.agg.challenge.gamethree.game.domain.aggregate.entities;

import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;

public interface PlayVisitor {
    InputNumber play(Human player);
    InputNumber play(Machine player);
}
