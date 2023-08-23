package org.restAssured.example;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Example3 {

    String response = "{\n" +
            "\"dashboard\": {\n" +
            "\"purchaseAmount\": 910,\n" +
            "\"website\": \"rahulshettyacademy.com\"\n" +
            "},\n" +
            "\"courses\": [\n" +
            "{\n" +
            "\"title\": \"Selenium Python\",\n" +
            "\"price\": 50,\n" +
            "\"copies\": 6\n" +
            "},\n" +
            "{\n" +
            "\"title\": \"Cypress\",\n" +
            "\"price\": 40,\n" +
            "\"copies\": 4\n" +
            "},\n" +
            "{\n" +
            "\"title\": \"RPA\",\n" +
            "\"price\": 45,\n" +
            "\"copies\": 10\n" +
            "}\n" +
            "]\n" +
            "}";
    JsonPath path = new JsonPath(response);

    @Test
    void printCoursesCount() {
        System.out.println("Course count: " + path.getInt("courses.size()"));
    }

    @Test
    void printPurchaseAmount() {
        System.out.println("Purchase Amount :" + path.getString("dashboard.purchaseAmount"));
    }

    @Test
    void printFirstCourseTitle() {
        System.out.println("First Course Title :" + path.getString("courses[0].title"));
    }

    @Test
    void printAllCourseTitleWithPrice() {
        for (int i = 0; i < path.getInt("courses.size()"); i++) {
            System.out.println("Course Title : " + path.getString("courses[" + i + "].title"));
            System.out.println("Course Price : " + path.getInt("courses[" + i + "].price"));
            if (path.getString("courses[" + i + "].title").equals("RPA")) {
                System.out.println("Course Price : " + path.getInt("courses[" + i + "].price"));
                break;
            }
        }
    }

    @Test
    void verifyPriceSum() {
        int purchaseAmount = path.getInt("dashboard.purchaseAmount");
        int sum = 0;
        int priceVal;
        for (int i = 0; i < path.getInt("courses.size()"); i++) {
            priceVal = (path.getInt("courses[" + i + "].price")) * (path.getInt("courses[" + i + "].copies"));
            System.out.println("Course Price : " + path.getInt("courses[" + i + "].price"));
            sum = sum + priceVal;
        }
        boolean flag = (purchaseAmount == sum);
        System.out.println("Values match :" + flag);
        Assert.assertTrue(flag, "FAIL : Values mismatch.");
    }
}
