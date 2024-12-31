package org.restAssuredTraining;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;

public class petStoreAPIPractice {

    @Test
    void test1(){


        Response response = given()
                .pathParam("pathParameter1","v3")
                .pathParam("pathParameter2","pet")
                .pathParam("pathParameter3", "findByStatus")
                .queryParam("status", "sold")
                .when().log().uri().get("https://petstore3.swagger.io/api/{pathParameter1}/{pathParameter2}/{pathParameter3}")
                .then().statusCode(200).log().all().extract().response();

        System.out.println(response.asString());

        JSONArray jsonArray = new JSONArray(response.asString());

        // 1. verify id
        int expectedId = 5;
        int actualId = jsonArray.getJSONObject(0).getInt("id");
        Assert.assertEquals(actualId, expectedId, "Fail: id mismatch!");
//
//        // 2. verify dogName
        String expectedDogName = "Dog 2";
        String actualDogName = jsonArray.getJSONObject(0).getString("name");
        Assert.assertEquals(actualDogName, expectedDogName,"Fail: Dog Name mismatch!");
//
//        //3. verify category
        String expectedCategoryName = "Dogs";
        String actualCategoryName = jsonArray.getJSONObject(0).getJSONObject("category").get("name").toString();
        Assert.assertEquals(actualCategoryName, expectedCategoryName,"Fail: Category Name mismatch!");
//
//        //4. verify status
        String expectedStatus = "sold";
        String actualStatus = jsonArray.getJSONObject(0).getString("status");
        Assert.assertEquals(actualStatus, expectedStatus,"Fail: actual Status mismatch!");

        //5. verify photo Url's
        List<JSONArray> photoUrlArray = new ArrayList<>();

        //5.1 Extracting photourl
        for (int i=0;i< jsonArray.length();i++){
            JSONArray jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("photoUrls");
            photoUrlArray.add(jsonArray1);
        }

        String expectedPhotoUrl = "https://petstore3.swagger.io/resources/photos/623389095.jpg";
        String actualPhotoUrl = photoUrlArray.get(0).getString(0);
        Assert.assertEquals(actualPhotoUrl, expectedPhotoUrl,"Fail: actual photoUrl mismatch!");
    }
}

//Code from chat GPT
//package org.restAssuredTraining;
//
//import io.restassured.response.Response;
//import org.json.JSONArray;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import static io.restassured.RestAssured.*;
//
//public class PetStoreAPIPractice {
//
//    private static final String BASE_URL = "https://petstore3.swagger.io/api";
//    private static final String FIND_BY_STATUS_PATH = "/{apiVersion}/{resource}/{action}";
//
//    @Test
//    void testPets() {
//        Response response = given()
//                .pathParam("apiVersion", "v3")
//                .pathParam("resource", "pet")
//                .pathParam("action", "findByStatus")
//                .queryParam("status", "sold")
//                .when().log().uri()
//                .get(BASE_URL + FIND_BY_STATUS_PATH)
//                .then().statusCode(200).log().all().extract().response();
//
//        JSONArray jsonArray = new JSONArray(response.asString());
//
//        // Verify ID
//        Assert.assertEquals(jsonArray.getJSONObject(0).getInt("id"), 5, "Fail: id mismatch!");
//
//        // Verify Name
//        Assert.assertEquals(jsonArray.getJSONObject(0).getString("name"), "Dog 2", "Fail: Dog Name mismatch!");
//
//        // Verify Category
//        Assert.assertEquals(jsonArray.getJSONObject(0).getJSONObject("category").getString("name"), "Dogs", "Fail: Category Name mismatch!");
//
//        // Verify Status
//        Assert.assertEquals(jsonArray.getJSONObject(0).getString("status"), "sold", "Fail: Status mismatch!");
//
//        // Verify Photo URLs
//        JSONArray photoUrls = jsonArray.getJSONObject(0).getJSONArray("photoUrls");
//        Assert.assertEquals(photoUrls.getString(0), "url1", "Fail: actual photoUrl mismatch!");
//    }
//}

