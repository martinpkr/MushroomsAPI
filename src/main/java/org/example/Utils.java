package org.example;

import com.mushrooms.dao.Datasource;

import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
     public static final String TABLE_NAME = "mushrooms";

    public static Datasource getDataSource(){
        return new Datasource();
    }

    public static void add(Statement statement, int id, String latinName, String englishName, String color, int eatable, int poisonous) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (" + id + ", '" + latinName + "', '" + englishName + "', '" + color + "', " + eatable + ", " + poisonous + ")";
        statement.execute(query);
    }

}
