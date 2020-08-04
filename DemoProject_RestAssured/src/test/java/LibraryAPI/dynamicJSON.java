package LibraryAPI;

import files.payLoad;
import files.reusableJSONPATH;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class dynamicJSON {

@Test(dataProvider = "BooksData")
    public void addBook(String isbn, String  aisle)
{
    RestAssured.baseURI = "http://216.10.245.166";
    String addbookResponse = given().log().all().header("Content-Type","application/json")
            .body(payLoad.addBook(isbn,aisle))
            .when().post("/Library/Addbook.php")
            .then().log().all().assertThat().statusCode(200).extract().response().asString();

    JsonPath js = reusableJSONPATH.rowtoJSON(addbookResponse);
    String bookID = js.get("ID");


    System.out.println("The Id is  " + bookID);


}


@Test

public void deleteBooks(){


}


@DataProvider(name ="BooksData")
    public Object[][] getdata()
{
    return new Object[][] {{"aabbc","2535"},{"aabbd","998877"},{"eeuhj","69785"}};

}


}


