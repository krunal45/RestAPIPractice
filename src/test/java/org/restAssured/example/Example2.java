package org.restAssured.example;

import io.restassured.path.json.JsonPath;

import java.util.*;

public class Example2 {
    String dummyResponse = "{\n" +
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
            "} ";
    JsonPath path = new JsonPath(dummyResponse);


    int getCoursesCount() {
        return path.getList("courses").size();
    }

    int getPurchaseAmount() {
        return path.getInt("dashboard.purchaseAmount");
    }

    String getFirstCourseTitle(List<Map> courses) {
        return courses.get(0).get("title").toString();
    }


    public static void main(String[] args) {
        Example2 obj = new Example2();
        Map<String, Object> course1 = new HashMap<>();
        Map<String, Object> course2 = new HashMap<>();
        Map<String, Object> course3 = new HashMap<>();
        List<Map> courses = new ArrayList<>();
        int sumTotal = 0;
        course1.put("title", "Selenium Python");
        course1.put("price", 50);
        course1.put("copies", 6);
        course2.put("title", "Cypress");
        course2.put("price", 40);
        course2.put("copies", 4);
        course3.put("title", "RPA");
        course3.put("price", 45);
        course3.put("copies", 10);
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        System.out.println("Total Course Count :" + obj.getCoursesCount());
        System.out.println("Purchase Amount :" + obj.getPurchaseAmount());
        System.out.println("First Course Title : " + obj.getFirstCourseTitle(courses));
        for (Map course : courses) {
            System.out.println("Course Title :" + course.get("title"));
            System.out.println("Cource Price :" + course.get("price"));
            int copies = (course.get("title").equals("RPA")) ? (Integer) course.get("copies") : -1;
            System.out.println("No of RPA Copies  :" + copies);
            sumTotal = sumTotal + ((Integer) course.get("price") * (Integer)course.get("copies"));
        }
        System.out.println("sumTotal :"+sumTotal);
        System.out.println("PurchaseAmount :"+obj.getPurchaseAmount());
        boolean flag = sumTotal == obj.getPurchaseAmount();
        System.out.println("Purchase amount matches with overall price count summation." + flag);
    }
}
