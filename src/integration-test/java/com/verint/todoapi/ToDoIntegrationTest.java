package com.verint.todoapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ToDoIntegrationTest {

    @Before
    public void config(){
        if(System.getenv("url") == null){
            RestAssured.baseURI = "http://localhost:8080";
        }
        else{
            RestAssured.baseURI = System.getenv("url");
        }

    }

    @Test
    void postRequest_ReturnCreatedToDo(){
        given()
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .body("""
                      {"name":"Get Test"}
                      """)
                .when().post("/todos");

        given()
                .when().get("/todos")
                .then().assertThat().statusCode(200)
                .body("id", Matchers.hasItem(1))
                .body("name", Matchers.hasItem("Get Test"));
    }

    @Test
    void delete_ToDoExists_SuccessCode(){
        given()
                .contentType(ContentType.JSON)
                .body("""
                      {"name":"Get Test"}
                      """)
                .when().post("/todos");
        given()
                .when().delete("/todos/1")
                .then().assertThat().statusCode(204);
    }
}


