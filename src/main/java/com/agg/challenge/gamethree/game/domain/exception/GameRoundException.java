package com.agg.challenge.gamethree.game.domain.exception;

public class GameRoundException extends RuntimeException{

    /**
     * Handle Game Round Exception
     * @param message
     */
    public GameRoundException(String message){
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
