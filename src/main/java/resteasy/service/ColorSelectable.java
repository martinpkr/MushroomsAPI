package resteasy.service;

import com.mushrooms.dao.Mushroom;

import java.util.ArrayList;

public interface ColorSelectable {
    public static ArrayList<Mushroom> getByColor(ArrayList<Mushroom> list,String color){
        ArrayList<Mushroom> currentColorMushroomsList = new ArrayList<>();
        for (Mushroom mushroom:
             list) {
            if(mushroom.getColor().contains(color)){
                currentColorMushroomsList.add(mushroom);
            }
        }
        return currentColorMushroomsList;
    }
}
