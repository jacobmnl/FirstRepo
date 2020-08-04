package oAuthTest;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pojo.getCourse;

import static io.restassured.RestAssured.given;

public class oAuthTest {

    public static void main(String[] args) throws InterruptedException {

//        System.setProperty("webdriver.chrome.driver","C://Selenium drivers//chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://accounts.google.com/signin/oauth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&o2v=2&as=C-dFPXjGM3ZzjImMmQISRQ&flowName=GeneralOAuthFlow");
//        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
//        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
//        driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
//        Thread.sleep(3000);
//        String url = driver.getCurrentUrl();
        String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2FzQHyN1_i8AplfMHR6T0dH2rNVx-ooj3RalXlOE9B6f8R06wLXpMBab-xiYJFD4FJYSQJ458anQpdTfUNP98o3A8&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#\n";
        String partialCode  = url.split("code=")[1];
        String Code = partialCode.split("&scope")[0];
        System.out.println("The code is  " + Code);


        String accessTokenResponse =  given()
                .urlEncodingEnabled(false)
                .queryParams("code",Code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

       JsonPath js = new JsonPath(accessTokenResponse);
       String  accessToken = js.getString("access_token");


        getCourse gc = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php")
                .as(getCourse.class);


       // System.out.println(response);



    }
}
