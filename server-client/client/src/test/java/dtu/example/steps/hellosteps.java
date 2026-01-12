package test.java.dtu.example.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class hellosteps {
  private String result;
  private HelloClient helloClient = new HelloClient();
    @When("I call the hello service")
    public void iCallTheHelloService() {
    result= helloClient.hello();
      
  }
  @Then("I get the answer {string}")
  public void iGetTheAnswer(String string) {
    assertEquals(string, result);
      
  }
}
