package org.example;

import com.mushrooms.models.Mushroom;

import java.lang.reflect.Array;
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
