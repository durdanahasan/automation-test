package com.grocery.tests.auth;
import com.grocery.clients.AuthClient;

import com.grocery.models.request.RegisterClientRequest;
import com.grocery.specs.ResponseSpecs;
import com.grocery.tests.BaseTest;
import com.grocery.utils.DataGenerator;
import org.testng.annotations.Test;

public class AuthNegativeTest extends BaseTest {
    AuthClient authClient = new AuthClient();
    public static String accessToken;


    @Test
    public void registerWithDuplicateEmail() {
        RegisterClientRequest body = new RegisterClientRequest(
                DataGenerator.randomName(), DataGenerator.randomEmail());
        authClient.register(body);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(409));

    }

    @Test(description = "BUG-02: Same email in upper case should return 409")
    public void registerWithDuplicateEmailUpperCase() {
        String name = DataGenerator.randomName();
        String email = DataGenerator.randomEmail();
        String upperEmail = email.toUpperCase();
        RegisterClientRequest body = new RegisterClientRequest(name, email);
        RegisterClientRequest upperBody = new RegisterClientRequest(name, upperEmail);
        authClient.register(body);
        authClient.register(upperBody)
                .then()
                .spec(ResponseSpecs.errorResponse(409));

    }

    @Test
    public void registerWithoutClientName() {
        String email = DataGenerator.randomEmail();
        RegisterClientRequest body = new RegisterClientRequest(null , email);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));

    }

    @Test
    public void registerWithoutClientEmail() {
        String name = DataGenerator.randomName();
        RegisterClientRequest body = new RegisterClientRequest(name, null);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));

    }

    @Test
    public void registerWithEmptyJsonBody() {
        RegisterClientRequest body = new RegisterClientRequest(null, null);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));
    }

    @Test
    public void registerWithoutBody() {
       authClient.registerWithoutBody()
               .then()
               .spec(ResponseSpecs.errorResponse(400));
    }

    @Test
    public void registerWithEmptyClientName() {
        String name = "";
        String email = DataGenerator.randomEmail();
        RegisterClientRequest body = new RegisterClientRequest(name, email);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));
    }

    @Test
    public void registerWithEmptyClientEmail() {
        String name = DataGenerator.randomName();
        String email = "";
        RegisterClientRequest body = new RegisterClientRequest(name, email);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));
    }

    @Test
    public void registerWithInvalidEmailFormat() {
        String name = DataGenerator.randomName();
        String email = "durdanainvalidemail";
        RegisterClientRequest body = new RegisterClientRequest(name, email);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));
    }

    @Test
    public void registerWithEmailWithoutDomain() {
        String name = DataGenerator.randomName();
        String email = "durdana@";
        RegisterClientRequest body = new RegisterClientRequest(name, email);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));
    }

    @Test(description = "BUG-03: Numeric clientName should return 400")
    public void registerWithNumericClientName() {
        String email = DataGenerator.randomEmail();
        String rawJson = "{\"clientName\": 12345, \"clientEmail\": \"" + email + "\"}";
        authClient.registerWithRawBody(rawJson)
                .then()
                .spec(ResponseSpecs.errorResponse(400));
    }

    @Test
    public void registerWithNumericClientEmail() {
        String name = DataGenerator.randomName();
        String email = "123";
        RegisterClientRequest body = new RegisterClientRequest(name, email);
        authClient.register(body)
                .then()
                .spec(ResponseSpecs.errorResponse(400));
    }
}


