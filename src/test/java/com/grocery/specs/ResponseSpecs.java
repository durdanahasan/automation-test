package com.grocery.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class ResponseSpecs {

    public static ResponseSpecification errorResponse(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectBody("error", not(emptyOrNullString()))
                .expectBody("$", not(hasKey("accessToken")))
                .build();
    }
}