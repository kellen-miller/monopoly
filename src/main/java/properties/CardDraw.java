package properties;

import com.fasterxml.jackson.databind.JsonNode;

public class CardDraw extends Property {
    public CardDraw(int id, Type type, String name) {
        super(id, type, name);
    }

    public CardDraw(JsonNode node) {
        this(node.at("/id").asInt(), Type.CARD_DRAW, node.at("/name").asText());
    }
}