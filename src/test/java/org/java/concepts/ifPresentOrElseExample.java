/*
Certainly! Here's a beginner-level coding question based on the line of code you provided:

---

### **Question:**

You are given an `Optional<Integer>` object that may or may not contain an integer value. Write a Java program that performs the following:

1. If the `Optional` contains a value, print "The value is: <value>".
2. If the `Optional` is empty (i.e., does not contain a value), print "No value is present".

You must use the `ifPresentOrElse` method to handle these two cases.

---

### **Hints:**
- The `Optional` class is part of `java.util` package.
- The `ifPresentOrElse()` method takes two arguments:
  1. A lambda expression to handle the case when the value is present.
  2. A lambda expression to handle the case when the value is absent (empty).

---

### **Example 1:**
Input:
```java
Optional<Integer> value = Optional.of(10);
```
Output:
```
The value is: 10
```

### **Example 2:**
Input:
```java
Optional<Integer> value = Optional.empty();
```
Output:
```
No value is present
```

---

This question tests the student's understanding of `Optional` in Java and how to use `ifPresentOrElse` to handle both present and absent values.
 */
package org.java.concepts;

import java.util.Optional;

public class ifPresentOrElseExample {


    public static void main(String[] args) {
        Optional<Integer> totalPrice = Optional.of(20);
        totalPrice.ifPresentOrElse(System.out::println, ()-> System.out.println("Total Price is Empty"));
    }
}