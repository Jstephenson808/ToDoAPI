package com.verint.todoapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


// don't run when gradle build is run
// create new gradle build tests
// pass env variables from arguments in travis.yml
// create new dir call integration tests and then they will be kept away from gradle build tasks

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ToDoIntegrationTest {

    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

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


