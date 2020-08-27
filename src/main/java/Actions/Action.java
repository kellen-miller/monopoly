package Actions;

import game.GameState;

public interface Action {
    default void apply(GameState gameState) {
    }

    int getPriority();
}