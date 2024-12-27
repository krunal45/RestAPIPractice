package org.restAssuredTraining;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class authorizationExample {

    @Test
    void basicAuth(){
        given()
                .auth().basic("example_username", "example_password")
                .pathParam("pathParameter1","example_username")
                .pathParam("pathParameter2","example_password")
                .when().get("https://httpbin.org/basic-auth/{pathParameter1}/{pathParameter2}")
                .then().statusCode(200)
                .and()
                .body("authenticated", equalTo(true))
                .and()
                .body("user", equalTo("example_username"))
                .log().all();
    }

    @Test(priority = 1)
    void digestAuth(){
        given()
                .auth().digest("example_username", "example_password")
                .pathParam("pathParameter1","example_username")
                .pathParam("pathParameter2","example_password")
                .when().get("https://httpbin.org/basic-auth/{pathParameter1}/{pathParameter2}")
                .then().statusCode(200)
                .and()
                .body("authenticated", equalTo(true))
                .and()
                .body("user", equalTo("example_username"))
                .log().all();
    }

    @Test(priority = 2)
    void preEmptiveAuth(){
        given()
                .auth().preemptive().basic("example_username", "example_password")
                .pathParam("pathParameter1","example_username")
                .pathParam("pathParameter2","example_password")
                .when().get("https://httpbin.org/basic-auth/{pathParameter1}/{pathParameter2}")
                .then().statusCode(200)
                .and()
                .body("authenticated", equalTo(true))
                .and()
                .body("user", equalTo("example_username"))
                .log().all();
    }

    @Test(priority = 3)
    void bearerToken(){
        Map<String,String> header = new HashMap<>();
        header.put("bearerToken","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");

        given()
                .headers(header)
                .pathParam("pathParameter1", "users")
                .pathParam("pathParameter2","repos")
                .when().get("https://api.github.com/{pathParameter1}/{pathParameter2}")
                .then().statusCode(200)
                .and()
                .body("login", equalTo("repos"))
                .log().all();
    }

    @Test(priority = 4)
    void oAuth2Authentication(){

        given()
                .auth().oauth2("eyJzdiI6IjAwMDAwMiIsImFsZyI6IkhTNTEyIiwidiI6IjIuMCIsImtpZCI6ImJjOGJmOWUzLTZhMDItNGU3YS1iMDNkLTMwMjFmNGFhYzdlZCJ9.eyJ2ZXIiOjEwLCJhdWlkIjoiNTg2NTFhYmUwMDgzNTI1OGE1OGM3OWU1NmFlMzE2OTA1OTdmNzc1NmY3YTljODJjMmE4MWEwMTFmOWRmYjRkZiIsImNvZGUiOiJobkh2ZjNINkcydFdrRW1QaXhLUmhPd1hoNWUzYmhsYmciLCJpc3MiOiJ6bTpjaWQ6Y2JSald4X2hUR3lnZ19qM1B6T2dRIiwiZ25vIjowLCJ0eXBlIjowLCJ0aWQiOjAsImF1ZCI6Imh0dHBzOi8vb2F1dGguem9vbS51cyIsInVpZCI6IkJsMkJUUTN5U3ZLYlFBR3FjMG1NYlEiLCJuYmYiOjE3MzUzMDY2NTcsImV4cCI6MTczNTMxMDI1NywiaWF0IjoxNzM1MzA2NjU3LCJhaWQiOiJmY3VEMS1KV1JuU3diYkI4cFc0N3ZnIn0.vaVokUSWh_WiSaPnhVo8_x_yFSPFTGgktzpGuCnOcPHa_jwn_o7J4MtdweS5JfphTvhUCoFDp6i8akToqf0Llw")
                .when().get("https://api.zoom.us")
                .then().statusCode(200);
    }

    @Test
    void apiKeyAuthorisation(){
        Map<String,String> queryParameters = new HashMap<>();
        queryParameters.put("api_key","TNDcXAAvQopNEXsUKbqIov9pYQL32cBgWGMT6Jv1");

        given()
                .queryParams(queryParameters)
                .pathParams("pathParameter","planetary/apod")
                .when().get("https://api.nasa.gov/{pathParameter}")
                .then().statusCode(200)
                .body("date", equalTo("2024-12-27"))
                .log().all();
    }
}