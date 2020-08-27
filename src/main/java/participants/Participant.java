package participants;

import properties.Property;

public abstract class Participant {
    int id;
    Property[] propertiesOwned = null;
    int cashAvailable = 20580;

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
