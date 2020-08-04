package DenoProject;

import files.payLoad;
import files.reusableJSONPATH;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class readFromFile {

    @Test
    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String addbookResponse = given().log().all().header("Content-Type", "application/json")
                .body(GenerateStringfromResource("C:\\Users\\Jacob\\Documents\\file.json"))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = reusableJSONPATH.rowtoJSON(addbookResponse);
        String bookID = js.get("ID");
        System.out.println("The Id is  " + bookID);

    }

    public static String GenerateStringfromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}




