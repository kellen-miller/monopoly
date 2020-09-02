package properties;

import com.fasterxml.jackson.databind.JsonNode;
import game.Game;
import participants.Participant;

public class Utility extends Property {
    final int price;
    final int mortgageValue;
    final Set set;
    Participant owner;

    public Utility(int id, Type type, String name, int price, int mortgageValue, Set set, Participant owner) {
        super(id, type, name);
        this.price = price;
        this.mortgageValue = mortgageValue;
        this.set = set;
        this.owner = owner;
    }

    public Utility(JsonNode node) {
        this(
                node.at("/id").asInt(),
                Type.REAL_ESTATE,
                node.at("/name").asText(),
                node.at("/price").asInt(),
                node.at("/mortgageValue").asInt(),
                Set.UTILITY,
                Game.bank
        );

    }
}
