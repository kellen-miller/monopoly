package game;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import participants.Bank;
import participants.Participant;
import participants.Player;
import properties.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Game {
    private static final int BANK_ID = 1;
    private static final int INITIAL_CASH = 20580;
    private static final int INITIAL_HOUSES = 32;
    public static Bank bank;
    private Map<Property.Set, List<Integer>> setIdMap;
    private Property[] properties;
    private int goLocation;
    private int jailLocation;
    private List<Participant.Token> tokens;
    private List<Player> players;
    private int freeParkingMoney = 0;

    public Game(int players) throws IOException {
        bank = new Bank(BANK_ID, new ArrayList<>(), INITIAL_CASH);
        this.setIdMap = new HashMap<>();
        createProperties();
        this.tokens = Arrays.asList(Participant.Token.values());
        createPlayers(players);
    }

    public static int getBankId() {
        return BANK_ID;
    }

    public static int getInitialCash() {
        return INITIAL_CASH;
    }

    public static int getInitialHouses() {
        return INITIAL_HOUSES;
    }

    public static Bank getBank() {
        return bank;
    }

    public static void setBank(Bank bank) {
        Game.bank = bank;
    }

    private void createProperties() throws IOException {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("propertyDetails.json")) {
            ArrayNode propsArray = (ArrayNode) new ObjectMapper().readTree(is).get("properties");
            this.properties = new Property[propsArray.size()];
            for (int i = 0; i < propsArray.size(); i++) {
                JsonNode props = propsArray.get(i);
                Property property = switch (Property.Type.valueOf(props.at("/type").asText())) {
                    case GO -> createGoProperty(props);
                    case REAL_ESTATE -> handleCreateRealEstateProperty(props);
                    case CARD_DRAW -> createCardDrawProperty(props);
                    case TAX -> createTaxProperty(props);
                    case JAIL -> createJailProperty(props);
                    case FREE_PARKING -> createFreeParkingProperty(props);
                    case GO_TO_JAIL -> createGoToJailProperty(props);
                };
                bank.getPropertiesOwned().add(property);
                this.properties[property.getId() - 1] = property;
            }
        }
    }

    private void createPlayers(int players) {
        this.players = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            Participant.Token token = this.tokens.remove(new Random().nextInt(this.tokens.size()));
            this.players.add(new Player(
                    bank.getId() + i + 1,
                    new ArrayList<>(),
                    1500,
                    token
            ));
        }
        Collections.shuffle(this.players);
    }

    private void addToSetMap(Property.Set set, int id) {
        this.setIdMap.computeIfAbsent(set, l -> new ArrayList<>()).add(id);
    }

    private Go createGoProperty(JsonNode prop) {
        this.goLocation = prop.at("/id").asInt() - 1;
        return new Go(
                goLocation,
                Property.Type.GO,
                prop.at("/name").asText()
        );
    }

    private Jail createJailProperty(JsonNode prop) {
        this.jailLocation = prop.at("/id").asInt() - 1;
        return new Jail(
                this.jailLocation,
                Property.Type.GO,
                prop.at("/name").asText()
        );
    }

    private GoToJail createGoToJailProperty(JsonNode prop) {
        return new GoToJail(
                prop.at("/id").asInt(),
                Property.Type.GO,
                prop.at("/name").asText()
        );
    }

    private FreeParking createFreeParkingProperty(JsonNode prop) {
        return new FreeParking(
                prop.at("/id").asInt(),
                Property.Type.GO,
                prop.at("/name").asText()
        );
    }

    private Property handleCreateRealEstateProperty(JsonNode prop) {
        Property.Set set = Property.Set.valueOf(prop.at("set").asText());
        int id = prop.at("/id").asInt();
        addToSetMap(set, id);
        if (set == Property.Set.RAILROAD) {
            return createRailRoadProperty(prop);
        } else if (set == Property.Set.UTILITY) {
            return createUtilityProperty(prop);
        } else {
            return createColorProperty(prop);
        }
    }

    private Railroad createRailRoadProperty(JsonNode prop) {
        int[] rent = new ObjectMapper().convertValue(prop.at("/rent"), int[].class);
        return new Railroad(
                prop.at("/id").asInt(),
                Property.Type.REAL_ESTATE,
                prop.at("/name").asText(),
                rent,
                prop.at("/mortgageValue").asInt(),
                bank,
                prop.at("/price").asInt(),
                Property.Set.RAILROAD
        );
    }

    private Utility createUtilityProperty(JsonNode prop) {
        return new Utility(
                prop.at("/id").asInt(),
                Property.Type.REAL_ESTATE,
                prop.at("/name").asText(),
                prop.at("/price").asInt(),
                prop.at("/mortgageValue").asInt(),
                Property.Set.UTILITY
        );
    }

    private Color createColorProperty(JsonNode prop) {
        int[] rent = new ObjectMapper().convertValue(prop.at("/rent"), int[].class);
        return new Color(
                prop.at("/id").asInt(),
                Property.Type.REAL_ESTATE,
                prop.at("/name").asText(),
                rent,
                prop.at("/mortgageValue").asInt(),
                bank,
                prop.at("/price").asInt(),
                Property.Set.valueOf(prop.at("/set").asText()),
                prop.at("/buildingCost").asInt()
        );
    }

    private CardDraw createCardDrawProperty(JsonNode prop) {
        return new CardDraw(
                prop.at("/id").asInt(),
                Property.Type.GO,
                prop.at("/name").asText()
        );
    }

    private Tax createTaxProperty(JsonNode prop) {
        if (prop.at("taxRate").isNull()) {
            return new Tax(
                    prop.at("/id").asInt(),
                    Property.Type.GO,
                    prop.at("/name").asText(),
                    prop.at("/taxAmount").asInt()
            );
        } else {
            return new Tax(
                    prop.at("/id").asInt(),
                    Property.Type.GO,
                    prop.at("/name").asText(),
                    prop.at("/taxAmount").asInt(),
                    prop.at("/taxRate").asDouble()
            );
        }
    }

    public int getGoLocation() {
        return goLocation;
    }

    public int getJailLocation() {
        return jailLocation;
    }

    public Map<Property.Set, List<Integer>> getSetIdMap() {
        return setIdMap;
    }

    public void setSetIdMap(Map<Property.Set, List<Integer>> setIdMap) {
        this.setIdMap = setIdMap;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setProperties(Property[] properties) {
        this.properties = properties;
    }

    public List<Participant.Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Participant.Token> tokens) {
        this.tokens = tokens;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getFreeParkingMoney() {
        return freeParkingMoney;
    }

    public void setFreeParkingMoney(int freeParkingMoney) {
        this.freeParkingMoney = freeParkingMoney;
    }
}