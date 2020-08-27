package game;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game(4);
        GameState gameState = new GameState(game);
        while (!gameState.isWinner()) {
            for (int i = 0; i < gameState.getActions().size(); i++) {
                //noinspection ConstantConditions
                gameState.getActions().poll().apply(gameState);
            }
        }
        System.out.println("Winner is "
                + gameState.getGame().getPlayers().get(0).getToken()
                + "!!!!!!!!!!!!!!!!!!");
    }
}
