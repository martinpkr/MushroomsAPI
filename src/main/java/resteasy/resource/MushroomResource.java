package resteasy.resource;

import com.mushrooms.models.Mushroom;
import resteasy.models.AddMushroomImpl;
import resteasy.models.AllMushrooms;
import resteasy.models.PoisonousMushrooms;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("mushrooms")
public class MushroomResource {
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allMushrooms(@QueryParam("color") String color) throws SQLException {
        AllMushrooms allMushrooms = new AllMushrooms(0);
        if(color != null && !color.isEmpty()){
            return Response.ok(allMushrooms.getAllMushroomsByColor(color)).build();
        }
        //return all mushrooms
        return Response.status(200)
                .entity(allMushrooms.getAllMushrooms())
                .build();
    }
    @Path("poisenous")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allPoisonMushrooms() throws SQLException{
        PoisonousMushrooms poisonousMushrooms = new PoisonousMushrooms();
        //return mushrooms that are poisenous
        return Response.ok(poisonousMushrooms.getAllPoisonousMushrooms()).build();
    }
    @Path("poisenous/{sort}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortedPoisonMushrooms(@PathParam("sort") String sort) throws SQLException {
        PoisonousMushrooms poisonousMushrooms = new PoisonousMushrooms();
        //return all poisonous mushrooms sorted with /asc or /desc
        if (sort.equals("asc")) {
            return Response.ok(poisonousMushrooms.getAllPoisonousMushrooms(1)).build();
        } else if (sort.equals("desc")) {
            return Response.ok(poisonousMushrooms.getAllPoisonousMushrooms(2)).build();
        }
        return null;
    }
    @Path("insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insert(Mushroom mushroom) throws SQLException {
        AddMushroomImpl.executeInsert(mushroom.getLatinName(), mushroom.getEnglishName(), mushroom.getColor(), mushroom.getEdible(), mushroom.getPoisonous());
       //insert new mushroom record
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity("ok")
                .build();

    }

}
