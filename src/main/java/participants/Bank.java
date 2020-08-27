package participants;


import properties.Property;

import java.util.List;

public class Bank extends Participant {
    public Bank(int id, List<Property> propertiesOwned, int cashAvailable) {
        super(id, propertiesOwned, cashAvailable);
    }
}
