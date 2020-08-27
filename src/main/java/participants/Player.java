package participants;

import game.Cards;
import properties.Property;

import java.util.List;

public class Player extends Participant {
    private Token token;
    private int currentSpace;
    private List<Cards> cards;

    public Player(int id, List<Property> propertiesOwned, int cashAvailable, Token token) {
        super(id, propertiesOwned, cashAvailable);
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(int currentSpace) {
        this.currentSpace = currentSpace;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }
}
