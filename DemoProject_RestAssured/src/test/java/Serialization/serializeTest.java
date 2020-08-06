package Serialization;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.addPlace;
import pojo.locaTion;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class serializeTest {

    public static void main(String[] args){

        addPlace ap = new addPlace();
        ap.setAccuracy(75);
        ap.setName("Royan House");
        ap.setPhone_number("+915658458222");
        ap.setAddress("12 walkway Moon,Ballybrack");
        ap.setWebsite("www.learnapi.com");
        ap.setLanguage("English-AU");
        List<String> mylist = new ArrayList<String>();
        mylist.add("Bag");
        mylist.add("Shoes");
        ap.setTypes(mylist);
        locaTion loc = new locaTion();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);
        ap.setLocation(loc);

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response resp = given().log().all().queryParams("key","qaclick123")
                .body(ap)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().log().all().statusCode(200).extract().response();
        String responseString = resp.asString();
        System.out.println(responseString);
        System.out.println(responseString);
        System.out.println(responseString);

        System.out.println("from USA");

        // Adding new changes from Australia on develop branch

        System.out.println("This is from develop branch");


        System.out.println("This is from develop branch from USA");
        System.out.println("This is from develop branch from USA");




    }


}
