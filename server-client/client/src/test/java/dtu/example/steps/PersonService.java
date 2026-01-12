package test.java.dtu.example.steps;
import jakarta.ws.rs.client.ClientBuilder;
public class PersonService {
  public Person getPerson() {
    return ClientBuilder.newClient()
        .target("http://localhost:8080")
        .path("/person")
        .request()
        .get(Person.class);
  }
    public Person updatePerson(String name, Integer age) {
        
        return ClientBuilder.newClient()
            .target("http://localhost:8080")
            .path("/person")
            .request()
            .post(jakarta.ws.rs.client.Entity.json(new Person(name, age)), Person.class);
    } 
}

