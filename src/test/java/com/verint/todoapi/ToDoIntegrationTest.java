package com.verint.todoapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.w3c.dom.CDATASection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;




@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ToDoIntegrationTest {

    //ToDo this will be the issue when in prod env
    private final static String BASE_URI = "http://localhost";

    @LocalServerPort
    private int port;

    @Before
    public void config(){
        RestAssured.baseURI = BASE_URI;
        RestAssured.port = port;
    }

    @Test
    void postTest(){
        given().port(port).contentType(ContentType.JSON).body("""
                                                        {"name":"James S"}
                                                        """)
                .when().post("/todos")
                .then().assertThat().statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("James S"));
    }

    @Test
    void exampleTest() {
        given().port(port).when().get("/todos")
                .then().assertThat().statusCode(200);
    }
}


