package properties;

import participants.Participant;

public class Color extends Property {
    final Set set;
    int buildingCount;
    final int buildingCost;

    public Color(int id, Type type, String name, int[] rent, int mortgageValue, Participant owner, int price,
                 Set set, int buildingCost){
        super(id, type, name, rent, mortgageValue, owner, price);
        this.set = set;
        this.buildingCost = buildingCost;
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