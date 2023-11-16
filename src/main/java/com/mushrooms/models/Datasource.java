package com.mushrooms.models;

import org.example.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DATABASE_CONNECTION = "testbase.db";
    public static final String DATABASE_TABLE = "mushrooms";
    public static final String COLUMN_ENGLISHNAME = "english_name";
    public static final String COLUMN_LATINNAME = "latin_name";
    public static final String COLUMN_ID = "id_";
    public static final String COLUMN_COLOR = "color";

    public static final String COLUMN_EDIBLE = "edible";
    public static final String COLUMN_POISONIOUS = "poisonous";
    public static final int ORDER_BY_NONE = 0;
    public static final int ORDER_BY_ASC = 1;
    public static final int ORDER_BY_DESC = 2;

    private static Connection conn;

    public boolean open() {

    try {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:/home/martin.kirilov/Desktop/Java/TestDataBase/" + DATABASE_CONNECTION);
        return true;
    }catch (SQLException e){
        System.out.println("Connection failed DB" + e.getMessage());
        return false;
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    }
    public void close(){
        try{
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Mushroom> queryMushrooms(int orderBy) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(DATABASE_TABLE);
        if(orderBy != ORDER_BY_NONE){
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ENGLISHNAME);
            sb.append(" COLLATE NOCASE ");
            if(orderBy == ORDER_BY_DESC){
                sb.append("DESC");
            }else{
                sb.append("ASC");
            }
        }
        List<Mushroom> mushrooms = new ArrayList<>();
        try(Statement statement = conn.createStatement()){
            ResultSet results = statement.executeQuery(sb.toString());
            while(results.next()){
                Mushroom mushroom = new Mushroom();
                mushroom.setId(results.getInt(COLUMN_ID));
                mushroom.setLatinName(results.getString(COLUMN_LATINNAME));
                mushroom.setEnglishName(results.getString(COLUMN_ENGLISHNAME));
                mushroom.setColor(results.getString(COLUMN_COLOR));
                mushroom.setEdible(results.getInt(COLUMN_EDIBLE));
                mushroom.setPoisonous(results.getInt(COLUMN_POISONIOUS));
                mushrooms.add(mushroom);
            }
            return mushrooms;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public List<Mushroom> getPoisonous(int orderBy,String latinName) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(DATABASE_TABLE);
        sb.append(" WHERE ");
        sb.append(COLUMN_LATINNAME + " = " + latinName);
        if(orderBy != ORDER_BY_NONE){

            sb.append(" ORDER BY ");
            sb.append(COLUMN_ENGLISHNAME);
            sb.append(" COLLATE NOCASE ");
            if(orderBy == ORDER_BY_DESC){
                sb.append("DESC");
            }else{
                sb.append("ASC");
            }
        }
        List<Mushroom> mushrooms = new ArrayList<>();
        try(Statement statement = conn.createStatement()){
            ResultSet results = statement.executeQuery(sb.toString());
            while(results.next()){
                Mushroom mushroom = new Mushroom();
                mushroom.setId(results.getInt(COLUMN_ID));
                mushroom.setLatinName(results.getString(COLUMN_LATINNAME));
                mushroom.setEnglishName(results.getString(COLUMN_ENGLISHNAME));
                mushroom.setColor(results.getString(COLUMN_COLOR));
                mushroom.setEdible(results.getInt(COLUMN_EDIBLE));
                mushroom.setPoisonous(results.getInt(COLUMN_POISONIOUS));
                mushrooms.add(mushroom);
            }
            return mushrooms;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void insertIntoTable(String latinName,String englishName,String colors,int edible,int poisonous) throws SQLException {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(DATABASE_TABLE + "(" + COLUMN_LATINNAME + ", " + COLUMN_ENGLISHNAME + ", "
        + COLUMN_COLOR + ", " + COLUMN_EDIBLE + ", " + COLUMN_POISONIOUS + ")");
        sb.append(" VALUES ('"+ latinName + "', '" + englishName + "', '" + colors + "', " + edible + ", " + poisonous + ")");

        try(Statement statement = conn.createStatement()){
            statement.execute(sb.toString());
        }
    }
    public static Connection getConn() {
        return conn;
    }
}

