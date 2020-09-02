package properties;

import com.fasterxml.jackson.databind.JsonNode;

public class GoToJail extends Property {
    public GoToJail(int id, Type type, String name) {
        super(id, type, name);
    }

    public GoToJail(JsonNode node) {
        this(node.at("/id").asInt(), Type.GO_TO_JAIL, node.at("/name").asText());
    }
}