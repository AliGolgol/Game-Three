package com.agg.challenge.gamethree.game.application.validator;


import com.agg.challenge.gamethree.config.PropertiesConfig;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputGameRound;
import com.agg.challenge.gamethree.game.domain.exception.GameRoundException;

public class InputDivideByThreeValidator implements Validator {

    private static final int DIVIDE;
    static {
        DIVIDE = Integer.parseInt(PropertiesConfig.getProperties().getProperty("game.divide"));
    }

    /**
     * Validate the input game round
     * @param input
     * @return
     */
    @Override
    public boolean validate(InputGameRound input) {
        int sum = input.sum();
        int result = sum % DIVIDE;
        return result == 0;
    }

    /**
     * Valid or not the input game round
     * @param input
     */
    @Override
    public void validateOrNot(InputGameRound input) {
        int sum = input.sum();
        if (sum % DIVIDE != 0) {
            throw new GameRoundException(String.format("The %s is not dividable by three", input.getNumber()));
        }
    }
}
