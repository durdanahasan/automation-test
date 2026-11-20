package com.grocery.tests.auth;
import com.grocery.clients.AuthClient;
import com.grocery.models.request.RegisterClientRequest;
import com.grocery.tests.BaseTest;
import com.grocery.utils.DataGenerator;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class AuthPositiveTest extends BaseTest {

    AuthClient authClient = new AuthClient();
    public static String accessToken;

    @Test
    public void registerNewClient() {
        RegisterClientRequest body = new RegisterClientRequest(
                DataGenerator.randomName(), DataGenerator.randomEmail());
        accessToken = authClient.register(body)
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("accessToken", not(emptyOrNullString()))
                .extract().path("accessToken");
        System.out.println("Token: "+ accessToken);
    }


}
