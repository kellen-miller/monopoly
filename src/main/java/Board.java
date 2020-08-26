import participants.Participant;
import properties.Property;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Board {
    Property[] properties;
    List<Participant.Token> tokens = Arrays.asList(Participant.Token.values());
    int housesAvailable = 32;

    public Board(int players){
        initialize(players);
    }

    private void initialize(int players){

    }

    private Property[] readInProperties(){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("propertyDetails.json"))
    }
}