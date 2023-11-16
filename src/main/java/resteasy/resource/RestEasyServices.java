package resteasy.resource;

import resteasy.models.cors;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/restapi")
public class RestEasyServices extends Application {

    private Set<Object> singletons = new HashSet<>();
    public RestEasyServices(){

        this.singletons.add(new MushroomResource());
        this.singletons.add(new cors());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
