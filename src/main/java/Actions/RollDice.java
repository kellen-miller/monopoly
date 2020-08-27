package Actions;

import game.GameState;
import participants.Player;

import java.util.Random;

public class RollDice implements Action {
    public static int rollDie(int[] die) {
        return die[new Random().nextInt(die.length)];
    }

    public void apply(GameState gameState) {
        int[] die1 = new int[]{1, 2, 3, 4, 5, 6};
        int[] die2 = new int[]{1, 2, 3, 4, 5, 6};
        int roll1 = rollDie(die1);
        int roll2 = rollDie(die2);
        Player player = gameState.getWhoseTurn();
        if (roll1 == roll2) {
            gameState.getRolledDoubles().merge(player, 1, Integer::sum);
        }
        if (gameState.getRolledDoubles().get(player) >= 3) {
            player.setCurrentSpace(gameState.getGame().getJailLocation());
        } else {
            player.setCurrentSpace(player.getCurrentSpace() + roll1 + roll2);

        }
    }
}
