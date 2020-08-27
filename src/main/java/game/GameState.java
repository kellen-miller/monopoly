package game;

import Actions.Action;
import Actions.RollDice;
import participants.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GameState {
    private Game game;
    private boolean winner;
    private int turnIndex;
    private Map<Player, Integer> rolledDoubles;
    private PriorityQueue<Action> actions;

    public GameState(Game game) {
        this.game = game;
        this.winner = false;
        this.turnIndex = -1;
        setRolledDoubles();
        setInitialActions();
    }

    private void setInitialActions() {
        this.actions = new PriorityQueue<>();
        actions.offer(new RollDice());
    }

    private void setRolledDoubles() {
        this.rolledDoubles = new HashMap<>();
        for (Player player : game.getPlayers()) {
            this.rolledDoubles.put(player, 0);
        }
    }

    public Player getWhoseTurn() {
        if (turnIndex >= game.getPlayers().size()) {
            return game.getPlayers().get(0);
        } else {
            return game.getPlayers().get(++turnIndex);
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Map<Player, Integer> getRolledDoubles() {
        return rolledDoubles;
    }

    public void setRolledDoubles(Map<Player, Integer> rolledDoubles) {
        this.rolledDoubles = rolledDoubles;
    }

    public PriorityQueue<Action> getActions() {
        return actions;
    }

    public void setActions(PriorityQueue<Action> actions) {
        this.actions = actions;
    }
}
