package com.agg.challenge.gamethree.game.application.validator;


import com.agg.challenge.gamethree.config.PropertiesConfig;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputGameRound;
import com.agg.challenge.gamethree.game.domain.exception.GameRoundException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InputRangeValidator implements Validator {

    private static final List<Integer> INPUT_NUMBERS = new CopyOnWriteArrayList<>();
    private static String INPUT_RANGE = PropertiesConfig.getProperties().getProperty("game.input_range");

    static {
        String[] inputs = INPUT_RANGE
                .split("[/s,.]");
        Arrays.stream(inputs).forEach(input -> {
            INPUT_NUMBERS.add(Integer.parseInt(input));
        });
    }

    /**
     * Validate the input game round
     * @param input is a {@link InputGameRound}
     * @return
     */
    @Override
    public boolean validate(InputGameRound input) {

        String[] inputs = INPUT_RANGE
                .split("[/s,.]");
        Arrays.stream(inputs).forEach(in -> {
            INPUT_NUMBERS.add(Integer.parseInt(in));
        });
        return INPUT_NUMBERS.contains(input.getAddedNumber());
    }

    /**
     * Valid or not the input game round
     * @param input
     */
    @Override
    public void validateOrNot(InputGameRound input) {
        if (!INPUT_NUMBERS.contains(input.getAddedNumber())) {
            throw new GameRoundException("The input number should be in (-1, 0, 1) range");
        }
    }
}
