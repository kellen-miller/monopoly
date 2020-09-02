package properties;

import com.fasterxml.jackson.databind.JsonNode;

public class Jail extends Property {
    public Jail(int id, Property.Type type, String name) {
        super(id, type, name);
    }

    public Jail(JsonNode node) {
        this(node.at("/id").asInt(), Type.JAIL, node.at("/name").asText());
    }
}
