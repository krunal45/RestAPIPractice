package org.restAssuredTraining;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class createCardToken {

    //Creating requestBody using HashMap.

    @Test(priority = 1,enabled = false)
    void createToken1(){
        HashMap data = new HashMap();
        data.put("type", "card");
        data.put("number", "4659105569051157");
        data.put("expiry_month", 6);
        data.put("expiry_year", 2026);
        data.put("name", "Bruce Wayne");
        data.put("cvv", "956");

        HashMap<String,String> billing_address = new HashMap<>();
        billing_address.put("address_line1", "Checkout.com");
        billing_address.put("address_line2", "90 Tottenham Court Road");
        billing_address.put("city", "London");
        billing_address.put("state", "st");
        billing_address.put("zip", "W1T 4TJ");
        billing_address.put("country", "GB");

        HashMap<String,String> phone = new HashMap<>();
        phone.put("country_code", "+1");
        phone.put("number", "415 555 2671");

        data.put("billing_address",billing_address);
        data.put("phone",phone);

        given()
                .contentType("application/json")
                .header("Authorization","Bearer pk_sbox_s4gepzrfsicj2af7il4wb25fnm7")
                .body(data)
                .when().post("https://api.sandbox.checkout.com/tokens")
                .then().statusCode(201)
                .body("expiry_month", equalTo(6))
                .body("expiry_year", equalTo(2026))
                .body("bin", equalTo("465910"))
                .body("last4", equalTo("1157"))
                .body("name", equalTo("Bruce Wayne"))
                .body("billing_address.address_line1", equalTo("Checkout.com"))
                .body("billing_address.address_line2", equalTo("90 Tottenham Court Road"))
                .body("billing_address.city", equalTo("London"))
                .body("billing_address.state", equalTo("st"))
                .body("billing_address.zip", equalTo("W1T 4TJ"))
                .body("billing_address.country", equalTo("GB"))
                .body("phone.country_code", equalTo("+1"))
                .body("phone.number", equalTo("415 555 2671"))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Connection", "keep-alive")
                .log().all();
    }


    //Creating requestBody using org.Json.
    @Test(priority = 2,enabled = false)
    void createToken2(){
        JSONObject data = new JSONObject();
        data.put("type", "card");
        data.put("number", "4659105569051157");
        data.put("expiry_month", 6);
        data.put("expiry_year", 2026);
        data.put("name", "Bruce Wayne");
        data.put("cvv", "956");

        HashMap<String,String> billing_address = new HashMap<>();
        billing_address.put("address_line1", "Checkout.com");
        billing_address.put("address_line2", "90 Tottenham Court Road");
        billing_address.put("city", "London");
        billing_address.put("state", "st");
        billing_address.put("zip", "W1T 4TJ");
        billing_address.put("country", "GB");

        HashMap<String,String> phone = new HashMap<>();
        phone.put("country_code", "+1");
        phone.put("number", "415 555 2671");

        data.put("billing_address",billing_address);
        data.put("phone",phone);

        given()
                .contentType("application/json")
                .header("Authorization","Bearer pk_sbox_s4gepzrfsicj2af7il4wb25fnm7")
                .body(data.toString())
                .when().post("https://api.sandbox.checkout.com/tokens")
                .then().statusCode(201)
                .body("expiry_month", equalTo(6))
                .body("expiry_year", equalTo(2026))
                .body("bin", equalTo("465910"))
                .body("last4", equalTo("1157"))
                .body("name", equalTo("Bruce Wayne"))
                .body("billing_address.address_line1", equalTo("Checkout.com"))
                .body("billing_address.address_line2", equalTo("90 Tottenham Court Road"))
                .body("billing_address.city", equalTo("London"))
                .body("billing_address.state", equalTo("st"))
                .body("billing_address.zip", equalTo("W1T 4TJ"))
                .body("billing_address.country", equalTo("GB"))
                .body("phone.country_code", equalTo("+1"))
                .body("phone.number", equalTo("415 555 2671"))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Connection", "keep-alive")
                .log().all();
    }

    //Creating requestBody using POJO class.
    @Test(priority = 3,enabled = false)
    void createToken3(){
    POJOTestCreateClass data = new POJOTestCreateClass();
    data.setType("card");
    data.setNumber("4659105569051157");
    data.setExpiry_month(6);
    data.setExpiry_year(2026);
    data.setName("Bruce Wayne");
    data.setCvv("956");

    HashMap<String,String> billing_address = new HashMap<>();
    billing_address.put("address_line1", "Checkout.com");
    billing_address.put("address_line2", "90 Tottenham Court Road");
    billing_address.put("city", "London");
    billing_address.put("state", "st");
    billing_address.put("zip", "W1T 4TJ");
    billing_address.put("country", "GB");
    data.setBilling_address(billing_address);

    HashMap<String,String> phone = new HashMap<>();
    phone.put("country_code", "+1");
    phone.put("number", "415 555 2671");

    data.setPhone(phone);

        given()
                .contentType("application/json")
                .header("Authorization","Bearer pk_sbox_s4gepzrfsicj2af7il4wb25fnm7")
                .body(data)
                .when().post("https://api.sandbox.checkout.com/tokens")
                .then().statusCode(201)
                .body("expiry_month", equalTo(6))
                .body("expiry_year", equalTo(2026))
                .body("bin", equalTo("465910"))
                .body("last4", equalTo("1157"))
                .body("name", equalTo("Bruce Wayne"))
                .body("billing_address.address_line1", equalTo("Checkout.com"))
                .body("billing_address.address_line2", equalTo("90 Tottenham Court Road"))
                .body("billing_address.city", equalTo("London"))
                .body("billing_address.state", equalTo("st"))
                .body("billing_address.zip", equalTo("W1T 4TJ"))
                .body("billing_address.country", equalTo("GB"))
                .body("phone.country_code", equalTo("+1"))
                .body("phone.number", equalTo("415 555 2671"))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Connection", "keep-alive")
                .log().all();
    }

    //Creating requestBody using external json file.
    @Test(priority = 4)
    void createToken4() throws FileNotFoundException {
        File file = new File("src/test/java/resources/requestBody/createCardToken.json");
        FileReader fileReader = new FileReader(file);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject data = new JSONObject(jsonTokener);

        given()
                .contentType("application/json")
                .header("Authorization","Bearer pk_sbox_s4gepzrfsicj2af7il4wb25fnm7")
                .body(data.toString())
                .when().post("https://api.sandbox.checkout.com/tokens")
                .then().statusCode(201)
                .body("expiry_month", equalTo(6))
                .body("expiry_year", equalTo(2026))
                .body("bin", equalTo("465910"))
                .body("last4", equalTo("1157"))
                .body("name", equalTo("Bruce Wayne"))
                .body("billing_address.address_line1", equalTo("Checkout.com"))
                .body("billing_address.address_line2", equalTo("90 Tottenham Court Road"))
                .body("billing_address.city", equalTo("London"))
                .body("billing_address.state", equalTo("st"))
                .body("billing_address.zip", equalTo("W1T 4TJ"))
                .body("billing_address.country", equalTo("GB"))
                .body("phone.country_code", equalTo("+1"))
                .body("phone.number", equalTo("415 555 2671"))
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Connection", "keep-alive")
                .log().all();
    }

}
