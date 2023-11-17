package com.mushrooms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.mushrooms.dao.Datasource.*;

public interface Queriable {

    static String buildQuery(String table, int orderAs, String orderBy){
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(table);
        if (orderAs != 0) {
            sb.append(" ORDER BY ");
            sb.append(orderBy);
            sb.append(" COLLATE NOCASE ");
            if (orderAs == 1) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        return sb.toString();
    }
    static String buildQueryWhere(String table, String where,String value){
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(table);
        sb.append(" WHERE ");
        sb.append(where + " = '" + value + "';" );
        return sb.toString();
    }
    static ArrayList<Mushroom> mappingQuery(ResultSet resultSet) throws SQLException {
        ArrayList<Mushroom> mushroomsList = new ArrayList<>();
        while (resultSet.next()) {
            Mushroom mushroom = new Mushroom();
            mushroom.setId(resultSet.getInt(COLUMN_ID));
            mushroom.setLatinName(resultSet.getString(COLUMN_LATINNAME));
            mushroom.setEnglishName(resultSet.getString(COLUMN_ENGLISHNAME));
            mushroom.setColor(resultSet.getString(COLUMN_COLOR));
            mushroom.setEdible(resultSet.getInt(COLUMN_EDIBLE));
            mushroom.setPoisonous(resultSet.getInt(COLUMN_POISONIOUS));
            mushroomsList.add(mushroom);
        }
        return mushroomsList;
    }

}
