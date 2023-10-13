package org.restAssured.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Example12 {
    JsonPath path;
    Response response;
    int bookingID;

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void createBookingTest(){
        response = RestAssured.given().header("Content-Type","application/json")
                .body("""
                        {
                            "firstname" : "Jim1",
                            "lastname" : "Brown1",
                            "totalprice" : 112,
                            "depositpaid" : true,
                            "bookingdates" : {
                                "checkin" : "2023-10-13",
                                "checkout" : "2023-10-14"
                            },
                            "additionalneeds" : "Lunch"
                        }""").when().log().all().post("/booking")
                .then().log().all().assertThat().statusCode(200).extract().response();

        path = new JsonPath(response.asString());
        bookingID = path.getInt("bookingid");
        Assert.assertEquals(path.getString("booking.firstname"), "Jim1", "FAIL: FirstName value mismatch!");
        Assert.assertEquals(path.getString("booking.bookingdates.checkin"), "2023-10-13","FAIL: Checkin values mismatch!");
        Assert.assertEquals(path.getString("booking.additionalneeds"), "Lunch", "FAIL: additionalneeds values mismatch!");
    }

    @Test
    public void getBookingTest(){
       response = RestAssured.given().accept("application/json")
               .when().get("/booking/"+bookingID).then().assertThat().statusCode(200).extract().response();
       path = new JsonPath(response.asString());
       Assert.assertEquals(path.getString("firstname"), "Jim1", "FAIL: FirstName value mismatch!");
       Assert.assertEquals(path.getString("bookingdates.checkout"), "2023-10-14", "FAIL: checkout value mismatch!");
       Assert.assertEquals(path.getString("additionalneeds"), "Lunch", "FAIL: additionalneeds value mismatch!");
    }
}
