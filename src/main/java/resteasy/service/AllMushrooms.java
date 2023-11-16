package resteasy.service;

import com.mushrooms.models.Datasource;
import com.mushrooms.models.Mushroom;
import org.example.ColorSelectable;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class AllMushrooms implements ColorSelectable {
    private ArrayList<Mushroom> allMushrooms ;

    public AllMushrooms(int orderBy) throws SQLException {
            allMushrooms = Datasource.queryMushrooms(orderBy);
    }
    public ArrayList<Mushroom> getAllMushrooms (){
        return allMushrooms;
    }
    public ArrayList<Mushroom> getAllMushroomsByColor (String color){
        ArrayList<Mushroom> list = ColorSelectable.getByColor(getAllMushrooms(),color);
        return list;
        }


}
