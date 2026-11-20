package com.grocery.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        RestAssured.baseURI = "https://simple-grocery-store-api.click";
    }
}