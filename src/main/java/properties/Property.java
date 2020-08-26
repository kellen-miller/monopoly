package properties;

import participants.Participant;

public abstract class Property {
    private final int id;
    private final Type type;
    private final String name;
    private Participant owner;
    private int price;
    private int[] rent;
    private int mortgageValue;

    public Property(int id, Type type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Property(int id, Type type, String name,
                    int[] rent, int mortgageValue) {
        this(id, type, name);
        this.rent = rent;
        this.mortgageValue = mortgageValue;
    }

    public Property(int id, Type type, String name, int[] rent, int mortgageValue,
                    Participant owner, int price) {
        this(id, type, name, rent, mortgageValue);
        this.owner = owner;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Participant getOwner() {
        return owner;
    }

    public void setOwner(Participant owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int[] getRent() {
        return rent;
    }

    public int getMortgageValue() {
        return mortgageValue;
    }

    enum Type{
        GO,
        REAL_ESTATE,
        CARD_DRAW,
        TAX,
        RAILROAD,
        CHANCE,
        JAIL,
        FREE_PARKING,
        GO_TO_JAIL,
        UTILITY
    }

    enum Set {
        BROWN,
        LIGHT_BLUE,
        PINK,
        ORANGE,
        RED,
        YELLOW,
        GREEN,
        DARK_BLUE,
        RAILROAD,
        UTILITY
    }
}