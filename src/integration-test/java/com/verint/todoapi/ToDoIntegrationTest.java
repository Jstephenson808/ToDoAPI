package com.verint.todoapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ToDoIntegrationTest {

    private final static String BASE_URI = "http://localhost";

    private int port = 8080;

    @Before
    public void config(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    void postRequest_ReturnCreatedToDo(){
        given()
                .port(port).contentType(ContentType.JSON)
                .body("""
                      {"name":"Post Test"}
                      """)
                .when().post("/todos")
                .then().assertThat().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Post Test"));
    }

    @Test
    void getRequest_ReturnToDos() {
        given()
                .port(port).contentType(ContentType.JSON)
                .body("""
                      {"name":"Get Test"}
                      """)
                .when().post("/todos");

        given()
                .port(port)
                .when().get("/todos")
                .then().assertThat().statusCode(200)
                .body("id", Matchers.hasItem(1))
                .body("name", Matchers.hasItem("Get Test"));
    }

    //after do get all and check
    @Test
    void delete_ToDoExists_SuccessCode(){
        given()
                .port(port).contentType(ContentType.JSON)
                .body("""
                      {"name":"Get Test"}
                      """)
                .when().post("/todos");
        given()
                .port(port)
                .when().delete("/todos/1")
                .then().assertThat().statusCode(204);
    }
}


