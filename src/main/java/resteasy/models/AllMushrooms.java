package resteasy.models;

import com.mushrooms.models.Datasource;
import com.mushrooms.models.Mushroom;
import org.example.ColorSelectable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class AllMushrooms implements ColorSelectable {
    public ArrayList<Mushroom> allMushrooms ;

    public AllMushrooms(int orderBy) throws SQLException {
        Datasource datasource = new Datasource();
        if(datasource.open()) {
        allMushrooms = (ArrayList<Mushroom>) datasource.queryMushrooms(orderBy);
        datasource.close();
    }
    }
    public ArrayList<Mushroom> getAllMushrooms (){
        return allMushrooms;
    }
    public ArrayList<Mushroom> getAllMushroomsByColor (String color){
        ArrayList<Mushroom> list = ColorSelectable.getByColor(getAllMushrooms(),color);
        return list;
        }


}
