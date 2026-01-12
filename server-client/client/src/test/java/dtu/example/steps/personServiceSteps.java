package test.java.dtu.example.steps;
import static org.junit.jupiter.api.Assertions.assertEquals;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class personServiceSteps {
  private Person result;
  PersonService service= new PersonService();
  @When("I call the person service")
  public void iCallThePersonService() {
    result = service.getPerson();
  }
    @When("I call the person service to update the person to name {string} and age {int}")
    public void iCallThePersonServiceToUpdateThePersonToNameAndAge(String string, Integer int1) {
        // Simulate updating the person; in a real scenario, this would involve a PUT/POST request
        result = service.updatePerson(string, int1);
  }

  @Then("I get the person name {string} and age {int}")
  public void iGetThePersonNameAndAge(String string, Integer int1) {
      assertEquals(string, result.name());
      assertEquals(int1, result.age());
  }
}
