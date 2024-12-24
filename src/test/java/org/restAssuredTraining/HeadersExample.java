package org.restAssuredTraining;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HeadersExample {

    @Test(enabled = false)
    void printHeaders() {
        Response response = given()
                .when().get("https://google.com");
         Headers headers = response.getHeaders();
         Iterator<Header> iterator = headers.iterator();
         while (iterator.hasNext())
             System.out.println(iterator.next());
    }

    @Test
    void verifyHeader(){
        given()
                .when().get("https://google.com")
                .then().statusCode(200)
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .header("Content-Encoding", "gzip")
                .header("Server", "gws");
    }
}
