package com.grocery.clients;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class StatusClient {
    public Response getStatus() {
        return given().get("/status");

    }

    public Response postStatus() {
        return given().post("/status");
    }
}
