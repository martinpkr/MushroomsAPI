package resteasy.service;

import java.sql.SQLException;

public interface Info {
    static int getCount() throws SQLException {
        return new AllMushrooms(0).getAllMushrooms().size();
    }

    static String getDocs(){
        return "Mushrooms API is used in the following manner:";
    }


}
