package org.example;

import com.mushrooms.dao.Datasource;
import com.mushrooms.dao.Mushroom;

import java.sql.*;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws SQLException {

        try {
            Datasource.initJDBConnectionPool();
            Mushroom amanita = Datasource.getOne("Amanita Muscaria");
            System.out.println(amanita.getEnglishName());
        }finally {
            Datasource.closeJDVConnectionPool();
        }


    }
}