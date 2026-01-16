package dtu.shop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceException_Exception;
import dtu.ws.fastmoney.BankService_Service;
import dtu.ws.fastmoney.User;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerRegistrationSteps {

	BankService bank = new BankService_Service().getBankServicePort();
	List<String> accounts = new ArrayList<>();

	CustomerFacade customerFacade = new CustomerFacade();
	Customer customer;
	String customerId;

	// Token-related fields
	private String token;
	private final HttpClient httpClient = HttpClient.newHttpClient();

	// Step: Given a registered customer with id "..."
	@Given("a registered customer with id {string}")
	public void aRegisteredCustomerWithId(String id) {
		customerId = id;
	}

	// Step: When the customer requests a token
	@When("the customer requests a token")
	public void theCustomerRequestsAToken() throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/customers/" + customerId + "/token"))
				.POST(HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		token = response.body();
		assertNotNull(token);
	}

	// Step: Then a token is returned
	@Then("a token is returned")
	public void aTokenIsReturned() {
		assertNotNull(token);
	}

	// Step: When the token is validated
	@When("the token is validated")
	public void theTokenIsValidated() throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/token/" + token + "/validate"))
				.POST(HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		assertEquals(customerId, response.body());
	}

	// Step: And the token cannot be reused
	@Then("the token cannot be reused")
	public void theTokenCannotBeReused() throws Exception {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("http://localhost:8080/token/" + token + "/validate"))
				.POST(HttpRequest.BodyPublishers.noBody())
				.build();

		Exception exception = assertThrows(Exception.class, () -> {
			httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		});
	}
}
