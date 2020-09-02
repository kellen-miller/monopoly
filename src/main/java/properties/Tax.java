package properties;

import com.fasterxml.jackson.databind.JsonNode;

public class Tax extends Property {
    final int taxAmount;
    final double taxRate;

    public Tax(int id, Type type, String name, int taxAmount, double taxRate) {
        super(id, type, name);
        this.taxAmount = taxAmount;
        this.taxRate = taxRate;
    }

    public Tax(JsonNode node) {
        this(
                node.at("/id").asInt(),
                Type.GO,
                node.at("/name").asText(),
                node.at("/taxAmount").asInt(),
                node.at("/taxRate").isNull() ? 1 : node.at("/taxRate").asDouble()
        );
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public double getTaxRate() {
        return taxRate;
    }
}