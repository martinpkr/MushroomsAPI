package resteasy.models;

import com.mushrooms.models.Mushroom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Info {
    static int getCount() throws SQLException {
        return new AllMushrooms(0).getAllMushrooms().size();
    }

    static String getDocs(){
        return "Mushrooms API is used in the following manner:";
    }


}
