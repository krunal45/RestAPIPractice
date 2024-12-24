package org.restAssuredTraining;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CookiesExample {

    @Test
    void getCookies(){
        Response response = given()
                .when().get("https://google.com");

//        String cookies = response.getCookie("AEC");
//        System.out.println("Cookie > "+cookies);

        //Fetching all available cookies.
        Map<String,String> allCookies = response.getCookies();
        allCookies.forEach((key, val) -> System.out.println(response.getCookie(key)));
    }
}
