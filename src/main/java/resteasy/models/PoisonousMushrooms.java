package resteasy.models;

import com.mushrooms.models.Mushroom;
import org.example.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PoisonousMushrooms {
    public PoisonousMushrooms() throws SQLException {
        List<Mushroom> poisonMushrooms = new ArrayList<Mushroom>();

    }
    public ArrayList<Mushroom> getAllPoisonousMushrooms() throws SQLException {
        AllMushrooms allMushrooms = new AllMushrooms(0);
        ArrayList<Mushroom> list = new ArrayList<>();

        for (Mushroom mushroom:
             allMushrooms.getAllMushrooms()) {
            if(mushroom.getPoisonous() == 1){
                list.add(mushroom);
            }
        }
        return list;
    }
    public ArrayList<Mushroom> getAllPoisonousMushrooms(int sort) throws SQLException {
        AllMushrooms allMushrooms = new AllMushrooms(sort);
        ArrayList<Mushroom> list = new ArrayList<>();

        for (Mushroom mushroom:
                allMushrooms.getAllMushrooms()) {
            if(mushroom.getPoisonous() == 1){
                list.add(mushroom);
            }
        }
        return list;
    }
}
