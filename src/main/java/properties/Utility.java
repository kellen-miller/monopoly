package properties;

public class Utility extends Property {
    final int price;
    final int mortgageValue;

    Utility(int id, Type type, String name, int price, int mortgageValue) {
        super(id, type, name);
        this.price = price;
        this.mortgageValue = mortgageValue;
    }
}
