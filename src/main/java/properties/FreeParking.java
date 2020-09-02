package properties;

import com.fasterxml.jackson.databind.JsonNode;

public class FreeParking extends Property {
    public FreeParking(int id, Type type, String name) {
        super(id, type, name);
    }

    public FreeParking(JsonNode node) {
        this(node.at("/id").asInt(), Type.FREE_PARKING, node.at("/name").asText());
    }
}