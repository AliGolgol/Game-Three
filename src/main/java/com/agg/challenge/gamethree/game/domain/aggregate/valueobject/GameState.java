package com.agg.challenge.gamethree.game.domain.aggregate.valueobject;

import java.util.Objects;

public class GameState {
    private final Status status;

    public GameState(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return status == gameState.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
