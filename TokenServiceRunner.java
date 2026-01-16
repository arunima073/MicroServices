package dtu.pay;

import dtu.pay.application.TokenService;
import dtu.pay.repository.TokenRepository;

public class TokenServiceRunner {

    public static void main(String[] args) {

        TokenRepository repo = new TokenRepository();
        TokenService service = new TokenService(repo);

        System.out.println("Creating token...");
        String token = service.createToken("customer-123");

        System.out.println("Token: " + token);
        System.out.println("Customer from token: "
                + service.getCustomerIdFromToken(token));

        System.out.println("Marking token as used...");
        service.markTokenAsUsed(token);

        System.out.println("Trying to reuse token (should fail)...");
        service.getCustomerIdFromToken(token);
    }
}
