package org.example;

import com.mushrooms.models.Datasource;
import com.mushrooms.models.Mushroom;
import resteasy.models.AllMushrooms;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws SQLException {

        Datasource datasource = new Datasource();
        datasource.open();
        if (datasource.open()) {
            Statement statement = datasource.getConn().createStatement();
//            Utils.add(statement, 2, "Amanita Muscaria", "Red Cap", "red,white", 0, 1);
//            Utils.add(statement, 3, "Hydnum repandum", "Oyster Mushroom", "white,grey", 1, 0);
//            Utils.add(statement, 4, "Flammulina velutipes", "Enoki Mushrooms", "brown,white", 1, 0);
//            Utils.add(statement, 5, "Cortinarius caperatus", "Gypsy Mushroom", "brown,white", 0, 0);
//            Utils.add(statement, 2, "Cantharellus cibarius", "Chanterelle Mushroom", "red,white", 0, 1);
//            Utils.add(statement, 7, "Agaricus arvensisv", "Horse Mushroom", "white", 1, 0);
//            Utils.add(statement, 8, "Adelphella babingtonii", "no english name", "brown", 1, 0);



//            datasource.close();
        }
//        List<Mushroom> myMushrooms = datasource.queryMushrooms(1);
//        for (Mushroom mushroom :
//                myMushrooms) {
//            System.out.println(mushroom.getEnglishName() + "( " + mushroom.getLatinName() + " ) is " + mushroom.getColor() + " color !");
//        }
        AllMushrooms allMushrooms = new AllMushrooms(0);
        ArrayList<Mushroom> list = allMushrooms.getAllMushroomsByColor("red");

    }
}