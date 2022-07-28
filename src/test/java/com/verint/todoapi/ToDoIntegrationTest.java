package com.verint.todoapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;




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
                .body("id", equalTo(1))
                .body("name", equalTo("Post Test"));
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
                .body("id", hasItem(1))
                .body("name", hasItem("Get Test"));
    }

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

    @Test
    void delete_ToDoDoesNotExist_NotFoundCode(){
        given()
                .port(port)
                .when().delete("/todos/999")
                .then().assertThat().statusCode(404);
    }

    @Test
    void post_noBody_UnsupportedMediaError(){
        given()
                .port(port).when()
                .post("todos")
                .then().assertThat().statusCode(415);
    }
}


