package com.verint.todoapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ToDoIntegrationTest {

    private static Integer nextId;
    private static Integer startId;

    @BeforeAll
    public static void config(){
        if(System.getenv("url") == null){
            RestAssured.baseURI = "http://localhost:8080";
        }
        else{
            RestAssured.baseURI = System.getenv("url");
        }
        startId = given()
                                .contentType(ContentType.JSON)
                                    .body("""
                                          {"name":"Next ID"}
                                          """)
                                .when()
                                    .post("/todos")
                                .then()
                                    .extract().path("id");
        nextId = startId + 1;
    }

    @Test
    @Order(1)
    void postRequest_ReturnCreatedToDo(){
        given()
                .contentType(ContentType.JSON)
                .body("""
                      {"name":"Post Test"}
                      """)
                .when().post("/todos")
                .then().assertThat().statusCode(200)
                .body("id", Matchers.equalTo(nextId))
                .body("name", Matchers.equalTo("Post Test"));
    }

    @Test
    @Order(2)
    void getRequest_ReturnToDos() {
        given()
                .when().get("/todos")
                .then().assertThat().statusCode(200)
                .body("id", Matchers.hasItem(nextId))
                .body("name", Matchers.hasItem("Post Test"));
    }

    @Test
    @Order(3)
    void delete_ToDoExists_SuccessCode(){
        given()
                .when().delete("/todos/" + nextId.toString())
                .then().assertThat().statusCode(204);
    }

    @AfterAll
    public static void cleanUp(){
        given()
                .when().delete("/todos/" + startId.toString());
    }
}


