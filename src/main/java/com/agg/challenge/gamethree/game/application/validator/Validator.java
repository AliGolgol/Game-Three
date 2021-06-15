package com.agg.challenge.gamethree.game.application.validator;


import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputGameRound;

public interface Validator {

    /**
     * Validate the input game round
     * @param input
     * @return
     */
    boolean validate(InputGameRound input);

    /**
     * Valid or not the input game round
     * @param input
     */
    void validateOrNot(InputGameRound input);
}
