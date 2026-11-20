package com.grocery.tests;
import com.grocery.clients.StatusClient;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class StatusTest extends BaseTest {

    StatusClient statusClient = new StatusClient();

    @Test
    public void getStatus() {
        statusClient.getStatus()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("status", equalTo("UP"))
                .time(lessThan(2000L));
    }

    @Test(description = "BUG-01: POST /status should return 405")
    public void statusWithWrongMethod() {
        statusClient.postStatus()
                .then()
                .statusCode(405);
    }
}