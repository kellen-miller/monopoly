package game;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import participants.Participant;
import properties.Go;
import properties.Property;
import properties.Railroad;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Board {
    Participant[] participants;
    Property[] properties;
    List<Participant.Token> tokens = Arrays.asList(Participant.Token.values());
    int housesAvailable = 32;

    public Board(int players) {
        initialize(players);
    }

    private void initialize(int players) {

    }

    private Property[] readInProperties() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("propertyDetails.json")) {
            ArrayNode props = (ArrayNode) new ObjectMapper().readTree(is).get("properties");
            for (JsonNode prop : props) {
                switch (Property.Type.valueOf(prop.at("/type").asText())) {
                    case GO -> createGoProperty(prop);
                    case REAL_ESTATE -> createRealEstateProperty(prop);
                }
            }
        }
    }

    private Go createGoProperty(JsonNode prop) {
        return new Go(prop.at("/id").asInt(),
                Property.Type.GO,
                prop.at("/name").asText()
        );
    }

    private Property handleRealEstateProperty(JsonNode prop) {
        if (prop.at("set").asText().equals("RAILROAD")) {
            return createRailRoadProperty(prop);
        } else if (prop.at("set").asText().equals("UTILITY")) {
            return createUtilityProperty(prop);
        } else {
            return createRealEstateProperty(prop);
        }
    }

    private Railroad createRailRoadProperty(JsonNode prop) {
        int[] rent = new ObjectMapper().convertValue(prop.at("/rent"), int[].class);
        return new Railroad(
                prop.at("/id").asInt(),
                Property.Type.RAILROAD,
                prop.at("/name").asText(),
                rent,
                prop.at("/mortgageValue").asInt(),
                participants[0],
                prop.at("/price").asInt(),
                Property.Set.RAILROAD
        );
    }

    private
}