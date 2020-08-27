package properties;

public abstract class Property {
    private final int id;
    private final Type type;
    private final String name;

    public Property(int id, Type type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
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

    public enum Type {
        GO,
        REAL_ESTATE,
        CARD_DRAW,
        TAX,
        JAIL,
        FREE_PARKING,
        GO_TO_JAIL
    }

    public enum Set {
        BROWN,
        LIGHT_BLUE,
        PINK,
        ORANGE,
        RED,
        YELLOW,
        GREEN,
        BLUE,
        RAILROAD,
        UTILITY
    }
}