package participants;

import properties.Property;

import java.util.List;

public abstract class Participant {
    public final int id;
    public List<Property> propertiesOwned;
    public int cashAvailable;

    public Participant(int id, List<Property> propertiesOwned, int cashAvailable) {
        this.id = id;
        this.propertiesOwned = propertiesOwned;
        this.cashAvailable = cashAvailable;
    }

    public int getId() {
        return id;
    }

    public List<Property> getPropertiesOwned() {
        return propertiesOwned;
    }

    public void setPropertiesOwned(List<Property> propertiesOwned) {
        this.propertiesOwned = propertiesOwned;
    }

    public int getCashAvailable() {
        return cashAvailable;
    }

    public void setCashAvailable(int cashAvailable) {
        this.cashAvailable = cashAvailable;
    }

    public enum Token {
        Scottish_Terrier,
        Battleship,
        Race_car,
        Penguin,
        T_Rex,
        Cat,
        Rubber_Ducky
    }
}
