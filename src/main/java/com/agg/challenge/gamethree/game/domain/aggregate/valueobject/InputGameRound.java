package com.agg.challenge.gamethree.game.domain.aggregate.valueobject;

import com.agg.challenge.gamethree.game.application.validator.InputDivideByThreeValidator;
import com.agg.challenge.gamethree.game.application.validator.Validator;

import java.util.Objects;

public class InputGameRound {
    int addedNumber;
    int number;

    /**
     * The input game round value object
     */
    public InputGameRound() {
        sum();
    }

    /**
     * The input game round value object
     * @param addedNumber
     * @param number
     */
    public InputGameRound(int addedNumber, int number) {
        this.addedNumber = addedNumber;
        this.number = number;
    }

    /**
     * Get the addition number
     * @return
     */
    public int getAddedNumber() {
        return addedNumber;
    }

    /**
     * Get the number
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sum of addition number and number
     * @return
     */
    public int sum() {
        return addedNumber + number;
    }

    /**
     * Validate the input number
     */
    public void validate(){
        Validator validator = new InputDivideByThreeValidator();
        validator.validateOrNot(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputGameRound that = (InputGameRound) o;
        return addedNumber == that.addedNumber && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addedNumber, number);
    }

    @Override
    public String toString() {
        return "InputGameRound{" +
                "additionNumber=" + addedNumber +
                ", number=" + number +
                '}';
    }
}

