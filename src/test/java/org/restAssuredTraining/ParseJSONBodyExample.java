package org.restAssuredTraining;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class ParseJSONBodyExample {

    //https://reqres.in/api/users?page=2

    @Test(enabled = false)
    void listUsers() {
        Response response = given()
                .pathParam("pathParameter", "users")
                .queryParam("page", 2)
                .when().get("https://reqres.in/api/{pathParameter}");

        //fetch first_name and store it in hash Map
        JsonPath jsonPath = response.jsonPath();
        List<String> actualFirstNameList = jsonPath.getList("data.first_name");
        List<String> expectedFirstNameList = new ArrayList<>(List.of("Michael","Lindsay","Tobias","Byron","George","Rachel"));
        Assert.assertTrue(actualFirstNameList.containsAll(expectedFirstNameList),"Fail: firstNames do not match!");
        System.out.println("firstName Matches!");
    }

    @Test
    void getBookDetails(){
        Response response = given()
                .pathParam("pathParameter", "get")
                .header("x-api-key", "PMAK-676d5cbf0d92b20001f4ea88-2fc24ad03f61247c6cfc40e183b8ea72ff")
                .when().get("https://21bcd2e2-1156-470c-bdc0-8a695629f5e4.mock.pstmn.io/{pathParameter}");

        JsonPath jsonPath = response.jsonPath();
        List<Float> priceList = jsonPath.getList("book.price");
        Optional<Float> totalPrice = priceList.stream().reduce(Float::sum);
        System.out.println("total Price > "+totalPrice);
    }

    /*
    ..chat GPT code for getBookDetails() method
    package org.restAssuredTraining;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.*;

public class BookService {

    // Store the base URL and API Key as constants for better maintainability
    private static final String BASE_URL = "https://21bcd2e2-1156-470c-bdc0-8a695629f5e4.mock.pstmn.io";
    private static final String API_KEY = System.getenv("API_KEY"); // Fetch API Key from environment variable

    // Method to get book details and calculate total price
    public void getBookDetails() {
        // Make the API request
        Response response = given()
                .pathParam("pathParameter", "get")
                .header("x-api-key", API_KEY)
                .when().get(BASE_URL + "/{pathParameter}");

        // Check if the response is successful
        if (response.statusCode() != 200) {
            System.err.println("Request failed with status code: " + response.statusCode());
            return;
        }

        // Parse the JSON response
        JsonPath jsonPath = response.jsonPath();
        List<Float> priceList = jsonPath.getList("book.price");

        // Check if the price list is empty
        if (priceList.isEmpty()) {
            System.out.println("No prices found in the response.");
            return;
        }

        // Calculate total price using Optional and stream reduce
        Optional<Float> totalPrice = priceList.stream().reduce(Float::sum);

        // Print the total price, handle empty Optional case
        totalPrice.ifPresentOrElse(
                price -> System.out.println("Total Price: " + price),
                () -> System.out.println("Unable to calculate total price")
        );
    }

    // Test the method (you can remove this in the actual production code)
    public static void main(String[] args) {
        BookService bookService = new BookService();
        bookService.getBookDetails();
    }
}

     */
}