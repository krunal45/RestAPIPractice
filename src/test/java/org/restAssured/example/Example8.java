package org.restAssured.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Example8 {
    ChromeOptions options = new ChromeOptions();
    JsonPath jsonPath;
    ChromeDriver driver;


    public String getAuthCode() {
//        WebDriverManager.chromedriver().setup();
//        options.addArguments("start-maximized", "open-incognito");
//        driver = new ChromeDriver(options);
//        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//        WebElement email = driver.findElement(By.cssSelector("input#identifierId"));
//        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
//        email.sendKeys("qaprayogshala");
//        password.sendKeys("Lu#@`ffERXZX--+=%", Keys.ENTER);
//        String url1 = driver.getCurrentUrl();
        String url1 = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0Adeu5BVIpw8D7GP1JtrHOe-judl8Ry0V0Gantqih3w7ZuLnQZDi4YtJ4laJ9woI4bBVi-w&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
        String url2 = url1.split("code=")[1];
        return url2.split("&scope")[0];
    }

    @BeforeTest
    public String getAuthToken() {
        String response = given().log().all().urlEncodingEnabled(false).queryParams("code", getAuthCode())
                .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret",
                        "erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
                .when().post("https://www.googleapis.com/oauth2/v4/token?code=4%2F0Adeu5BV0J8HhAtzbBqFT9iAklT9lavYtkb3ba13dLexeBLf9b9ok5FWiMM-hbeyoWpmasQ&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&client_secret=erZOWM9g3UtwNRj340YYaK_W&redirect_uri=https://rahulshettyacademy.com/getCourse.php&grant_type=authorization_code#")
                .then().extract().response().asPrettyString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        return jsonPath.getString("access_token");
    }

    @Test
    void request() {
        given().queryParam("access_token", getAuthToken())
                .when().get("https://rahulshettyacademy.com/getCourse.php?access_token=ya29.a0AfH6SMD-EytFH41srRFqd55ewFQGIJDcuEq-uhPn4UYeXmyRkY5NY0VBPYZgAk2zou8KRYZpspkrC3QtrhKS5McUsWoxCFpWoYEVFq3rN00eJCicj-_j9xv8Q-9gTumTGy5rJxgfkMrTVZSbtWnB2LSR0C1L")
                .then().assertThat().statusCode(200);
    }
}
