package properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.Game;
import participants.Participant;

public class Railroad extends Property {
    final int[] rent;
    final int mortgageValue;
    final Set set;
    int price;
    Participant owner;

    public Railroad(int id, Type type, String name,
                    int[] rent, int mortgageValue, Participant owner, int price, Set set) {
        super(id, type, name);
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.owner = owner;
        this.price = price;
        this.set = set;
    }

    public Railroad(JsonNode node) {
        this(
                node.at("/id").asInt(),
                Type.REAL_ESTATE,
                node.at("/name").asText(),
                new ObjectMapper().convertValue(node.at("/rent"), int[].class),
                node.at("/mortgageValue").asInt(),
                Game.bank,
                node.at("/price").asInt(),
                Set.RAILROAD
        );
    }

    public int[] getRent() {
        return rent;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Participant getOwner() {
        return owner;
    }

    public void setOwner(Participant owner) {
        this.owner = owner;
    }

    public Set getSet() {
        return set;
    }
}
