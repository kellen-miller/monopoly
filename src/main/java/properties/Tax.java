package properties;

public class Tax extends Property {
    final int taxAmount;
    final double taxRate;

    public Tax(int id, Type type, String name,
               int taxAmount, double taxRate) {
        super(id, type, name);
        this.taxAmount = taxAmount;
        this.taxRate = taxRate;
    }

    public Tax(int id, Type type, String name,
               int taxAmount) {
        super(id, type, name);
        this.taxAmount = taxAmount;
        this.taxRate = 0;
    }

    public int getTaxAmount(){
        return taxAmount;
    }

    public double getTaxRate() {
        return taxRate;
    }
}