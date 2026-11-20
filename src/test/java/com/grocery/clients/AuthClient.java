package com.grocery.clients;

import com.grocery.models.request.RegisterClientRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response register(RegisterClientRequest request) {
        return given()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/api-clients");
    }

    public Response registerWithoutBody() {
        return given()
                .post("/api-clients");
    }

    public Response registerWithRawBody(String rawJson) {
        return given()
                .contentType(ContentType.JSON)
                .body(rawJson)
                .post("/api-clients");
    }
}