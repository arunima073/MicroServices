package test.java.dtu.example.steps;
import jakarta.ws.rs.client.ClientBuilder;
public class HelloClient {
  
  public String hello() {
    return ClientBuilder.newClient()
        .target("http://localhost:8080")
        .path("/hello")
        .request()
        .get(String.class);
  }
}