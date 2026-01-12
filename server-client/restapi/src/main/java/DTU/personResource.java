package DTU;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/person")
public class personResource {
  personService service = new personService();
    //why do we need to create an instance of personService here?
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person hello() {
      return service.getPerson();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Person updatePerson(Person person) {
        return new Person("John Doe", 25);
    }
}
