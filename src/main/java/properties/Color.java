package properties;

import participants.Participant;

public class Color extends Property {
    final int[] rent;
    final int mortgageValue;
    int price;
    Participant owner;
    final Set set;
    int buildingCount;
    final int buildingCost;

    public Color(int id, Type type, String name, int[] rent, int mortgageValue, Participant owner,
                 int price, Set set, int buildingCost) {
        super(id, type, name);
        this.rent = rent;
        this.mortgageValue = mortgageValue;
        this.owner = owner;
        this.price = price;
        this.set = set;
        this.buildingCost = buildingCost;
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

    public int getBuildingCount() {
        return buildingCount;
    }

    public void setBuildingCount(int buildingCount) {
        this.buildingCount = buildingCount;
    }

    public int getBuildingCost() {
        return buildingCost;
    }
}