package RequestSpecBuilder;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.addPlace;
import pojo.locaTion;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class specBuilderTest {

    public static void main(String[] args){

        addPlace ap = new addPlace();
        ap.setAccuracy(75);
        ap.setName("Royans House");
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

       RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
               .setContentType(ContentType.JSON).build();

        ResponseSpecification responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

       RequestSpecification resp = given().spec(reqspec)
                .body(ap);

        Response response = resp.when().post("/maps/api/place/add/json")
                .then().spec(responsespec).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);

    }


}
