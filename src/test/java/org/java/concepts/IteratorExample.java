package org.java.concepts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Chickoo");
        fruits.add("Dragon");
        System.out.println("--Before Iteration--");
        fruits.forEach(System.out::println);

        fruits.removeIf(fruit -> fruit.equals("Chickoo"));

        System.out.println("--After Iteration--");
        fruits.forEach(System.out::println);
    }
}
