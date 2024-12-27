package org.restAssuredTraining;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class fakeDataGenerator {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String firstName,lastName,userName,email;
    fakeDataGenerator dataGenerator = new fakeDataGenerator();

    @Test
    void generateData(){
        Faker faker = new Faker();
        dataGenerator = new fakeDataGenerator();
        dataGenerator.setFirstName(faker.name().firstName());
        dataGenerator.setLastName(faker.name().lastName());
        dataGenerator.setUserName(faker.name().username());
        dataGenerator.setEmail(faker.internet().safeEmailAddress());

        System.out.println("firstName: "+getFirstName());
        System.out.println("lastName: "+getFirstName());
        System.out.println("userName: "+getFirstName());
        System.out.println("email: "+getFirstName());
    }
}
