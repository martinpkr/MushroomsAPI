package com.mushrooms.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Datasource implements Queriable {
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

    private static HikariDataSource dataSource;

    public static void initJDBConnectionPool() {
       try {
           Class.forName("org.sqlite.JDBC");
           HikariConfig config = new HikariConfig();
           config.setPoolName("SQLitePool");
           config.setDriverClassName("org.sqlite.JDBC");
           config.setJdbcUrl("jdbc:sqlite:/home/martin.kirilov/Desktop/Java/TestDataBase/" + DATABASE_CONNECTION);
           config.setConnectionTestQuery("SELECT 1");
           config.setMaxLifetime(60000); // 60 Sec
           config.setIdleTimeout(45000); // 45 Sec
           config.setMaximumPoolSize(50); // 50 Connections (including idle connections)
           dataSource = new HikariDataSource(config);

       }catch (ClassNotFoundException e ){
           e.printStackTrace();
       }
    //init connection with configuring relative path to the DB that is in the root dir of the project
    }
    public static void closeJDVConnectionPool(){
        dataSource.close();
    }

    public static ArrayList<Mushroom> queryMushrooms(int orderAs,String orderBy) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String queryAsString = Queriable.buildQuery(DATABASE_TABLE,orderAs,orderBy);
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryAsString)) {
                //changed all Statements to PreparedStatements
                ResultSet results = preparedStatement.executeQuery();
                return Queriable.mappingQuery(results);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
    public static Mushroom getOne(String param) throws SQLException {
        Connection connection = dataSource.getConnection();
        String query = Queriable.buildQueryWhere(DATABASE_TABLE,COLUMN_LATINNAME,param);

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet results = preparedStatement.executeQuery();
            System.out.println(results);

                Mushroom mushroom = new Mushroom();
                mushroom.setId(results.getInt(COLUMN_ID));
                mushroom.setLatinName(results.getString(COLUMN_LATINNAME));
                mushroom.setEnglishName(results.getString(COLUMN_ENGLISHNAME));
                mushroom.setColor(results.getString(COLUMN_COLOR));
                mushroom.setEdible(results.getInt(COLUMN_EDIBLE));
                mushroom.setPoisonous(results.getInt(COLUMN_POISONIOUS));

            return mushroom;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            connection.close();
        }
    }
//    public static void insertIntoTable(String latinName,String englishName,String colors,int edible,int poisonous) throws SQLException {
//        Connection connection = dataSource.getConnection();
//        StringBuilder sb = new StringBuilder("INSERT INTO ");
//        sb.append(DATABASE_TABLE + "(" + COLUMN_LATINNAME + ", " + COLUMN_ENGLISHNAME + ", "
//        + COLUMN_COLOR + ", " + COLUMN_EDIBLE + ", " + COLUMN_POISONIOUS + ")");
//        sb.append(" VALUES ('"+ latinName + "', '" + englishName + "', '" + colors + "', " + edible + ", " + poisonous + ")");
//
//        try(Statement statement = conn.createStatement()){
//            statement.execute(sb.toString());
//        }
//    }
}

