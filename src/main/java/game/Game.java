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
    private static final int INITIAL_BANK_CASH = 20580;
    private static final int INITIAL_PLAYER_CASH = 1500;
    private static final int INITIAL_HOUSES = 32;
    public static Bank bank;
    private static Map<Property.Set, List<Integer>> setIdMap;
    private static Property[] properties;
    private static int goLocation;
    private static int jailLocation;
    private static List<Participant.Token> tokens;
    private static List<Player> players;
    private static int freeParkingMoney = 0;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Game(int players) throws IOException {
        bank = new Bank(BANK_ID, new ArrayList<>(), INITIAL_BANK_CASH);
        setIdMap = new HashMap<>();
        createProperties();
        tokens = new ArrayList(Arrays.asList(Participant.Token.values()));
        createPlayers(players);
    }

    public static int getBankId() {
        return BANK_ID;
    }

    public static int getInitialCash() {
        return INITIAL_BANK_CASH;
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
            properties = new Property[propsArray.size()];
            for (int i = 0; i < propsArray.size(); i++) {
                JsonNode props = propsArray.get(i);
                Property property = switch (Property.Type.valueOf(props.at("/type").asText())) {
                    case GO -> {
                        goLocation = props.at("/id").asInt() - 1;
                        yield new Go(props);
                    }
                    case REAL_ESTATE -> {
                        Property.Set set = Property.Set.valueOf(props.at("/set").asText());
                        setIdMap.computeIfAbsent(set, l -> new ArrayList<>()).add(props.at("/id").asInt());
                        if (set == Property.Set.RAILROAD) {
                            yield new Railroad(props);
                        } else if (set == Property.Set.UTILITY) {
                            yield new Utility(props);
                        } else {
                            yield new Color(props);
                        }
                    }
                    case CARD_DRAW -> new CardDraw(props);
                    case TAX -> new Tax(props);
                    case JAIL -> {
                        jailLocation = props.at("/id").asInt() - 1;
                        yield new Jail(props);
                    }
                    case FREE_PARKING -> new FreeParking(props);
                    case GO_TO_JAIL -> new GoToJail(props);
                };
                properties[property.getId() - 1] = property;
            }
            bank.setPropertiesOwned(Arrays.asList(properties));
        }
    }

    private void createPlayers(int players) {
        Game.players = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            Game.players.add(new Player(
                    bank.getId() + i + 1,
                    new ArrayList<>(),
                    INITIAL_PLAYER_CASH,
                    tokens.remove(new Random().nextInt(tokens.size()))
            ));
            bank.updateBankBalance(-INITIAL_PLAYER_CASH);
        }
        Collections.shuffle(Game.players);
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
        Game.setIdMap = setIdMap;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setProperties(Property[] properties) {
        Game.properties = properties;
    }

    public List<Participant.Token> getTokens() {
        return tokens;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        Game.players = players;
    }

    public int getFreeParkingMoney() {
        return freeParkingMoney;
    }

    public void setFreeParkingMoney(int freeParkingMoney) {
        Game.freeParkingMoney = freeParkingMoney;
    }
}