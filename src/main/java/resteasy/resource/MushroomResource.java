package resteasy.resource;

import com.mushrooms.models.Datasource;
import com.mushrooms.models.Mushroom;
import resteasy.service.AddMushroomImpl;
import resteasy.service.AllMushrooms;
import resteasy.service.PoisonousMushrooms;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    @Path("{latinName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response oneMushroom(@Encoded @PathParam("latinName") String latinName) throws SQLException, UnsupportedEncodingException {
        String latinNamedecoded = URLDecoder.decode(latinName, "UTF-8");
        AllMushrooms allMushrooms = new AllMushrooms(0);
        //return mushroom by its latinName

        return Response.ok(Datasource.getOne(latinNamedecoded)).build();

    }
    @Path("poisenous/{sort}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sortedPoisonMushrooms(@Encoded @PathParam("sort") String sort) throws SQLException {
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
