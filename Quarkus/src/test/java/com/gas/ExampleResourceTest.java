package com.gas;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExampleResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }
    @Test
    public void testGetClientByIdEndpoint() {
        given()
                .when().get("clients/businessId?businessId=fsotocadena")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body("email", is("1"));
    }
}