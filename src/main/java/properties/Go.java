package properties;

import com.fasterxml.jackson.databind.JsonNode;

public class Go extends Property {
    public Go(int id, Type type, String name) {
        super(id, type, name);
    }

    public Go(JsonNode node) {
        this(node.at("/id").asInt(), Type.GO, node.at("/name").asText());
    }
}