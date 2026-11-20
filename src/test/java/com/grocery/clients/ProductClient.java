package com.grocery.clients;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ProductClient {
    public Response getAllProducts() {
        return given().get("/products");

    }
}


