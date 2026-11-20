package com.grocery.tests.product;
import com.grocery.clients.ProductClient;
import com.grocery.tests.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


public class ProductPositiveTest extends BaseTest{
    ProductClient productClient = new ProductClient();

    @Test
    public void getAllProducts() {
        productClient.getAllProducts()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", equalTo(20))
                .body("id", everyItem(instanceOf(Integer.class)))
                .body("category", everyItem(instanceOf(String.class)))
                .body("name", everyItem(instanceOf(String.class)))
                .body("inStock", everyItem(instanceOf(Boolean.class)));
    }
}
