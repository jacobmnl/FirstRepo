package DenoProject;

import files.payLoad;
//import io.gatling.jsonpath.JsonPath;

import files.reusableJSONPATH;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class basics {

    public static void main(String[] args) {

        // Add place API
        //given, - all inputs detail
        // when - Submit the API ( resource and http method  will go alwats in when method)
        // Then - validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(payLoad.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

      System.out.println(response);

        JsonPath js = new JsonPath(response);
        String placeid = js.getString("place_id");
        System.out.println("Place d is " + placeid);

// Update place
        String newAddress = "35 Stillorgan, Dublin";
        given().log().all().queryParam("key","qaclick123").header("Content","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeid+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n")
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        // get API

        String getResponse =  given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = reusableJSONPATH.rowtoJSON(getResponse);
        String responseAddress = js1.getString("address");
        System.out.println(responseAddress);
        Assert.assertEquals(responseAddress,newAddress);


    }

}
