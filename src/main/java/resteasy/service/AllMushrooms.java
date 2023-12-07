package resteasy.service;

import com.mushrooms.dao.Datasource;
import com.mushrooms.dao.Mushroom;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.SQLException;
import java.util.ArrayList;

@XmlRootElement
public class AllMushrooms implements ColorSelectable {
    private ArrayList<Mushroom> allMushrooms ;
    public AllMushrooms() throws SQLException {
        allMushrooms = Datasource.queryMushrooms(0,Datasource.COLUMN_ID);
    }

    public AllMushrooms(int orderAs) throws SQLException {
        this();
        allMushrooms = Datasource.queryMushrooms(orderAs,Datasource.COLUMN_ID);
    }
    public AllMushrooms(int orderAs,String orderBy) throws SQLException {
        this();
        allMushrooms = Datasource.queryMushrooms(orderAs,orderBy);
    }

    public ArrayList<Mushroom> getAllMushrooms (){
        return allMushrooms;
    }
    public ArrayList<Mushroom> getAllMushroomsByColor (String color){
        ArrayList<Mushroom> list = ColorSelectable.getByColor(getAllMushrooms(),color);
        return list;
        }


}
