package properties;

public class Utility extends Property {
    final int price;
    final int mortgageValue;
    final Set set;

    public Utility(int id, Type type, String name, int price, int mortgageValue, Set set) {
        super(id, type, name);
        this.price = price;
        this.mortgageValue = mortgageValue;
        this.set = set;
    }
}
