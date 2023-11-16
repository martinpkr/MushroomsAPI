package resteasy.service;

import com.mushrooms.models.Datasource;

import java.sql.SQLException;

public class AddMushroomImpl {

    public static void executeInsert(String latinName, String englishName, String colors, int edible, int poison) throws SQLException {
        if (latinName != null && !latinName.isEmpty() &&
                englishName != null && !englishName.isEmpty() &&
                colors != null && !colors.isEmpty()) {
            if (edible >= 0 && poison >= 0) {
                Datasource datasource = new Datasource();
//                Datasource.insertIntoTable(latinName, englishName, colors, edible, poison);
            } else {
                System.out.println("Invalid values for edible and poison. They must not be negative.");
            }
        } else {
            System.out.println("Invalid values for latinName, englishName, or colors. They must not be null or empty.");
        }
    }


}
