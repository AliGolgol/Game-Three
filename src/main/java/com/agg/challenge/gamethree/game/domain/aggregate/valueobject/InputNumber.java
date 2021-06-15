package com.agg.challenge.gamethree.game.domain.aggregate.valueobject;

import java.util.Objects;

public class InputNumber {
    int addedNumber;
    int number;

    public InputNumber(int addedNumber, int number) {
        this.addedNumber = addedNumber;
        this.number = number;
    }

    public int getAddedNumber() {
        return addedNumber;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputNumber that = (InputNumber) o;
        return addedNumber == that.addedNumber && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addedNumber, number);
    }
}
