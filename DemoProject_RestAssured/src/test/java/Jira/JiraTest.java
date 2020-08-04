package Jira;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import org.json4s.jackson.Json;
import org.testng.Assert;
import scala.Int;

import java.io.File;

import static io.restassured.RestAssured.*;

public class JiraTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8081/";
        SessionFilter session = new SessionFilter();

        String Response = given().header("Content-Type", "application/json").log().all()
                .body("{ \"username\": \"jacobmnl\", \"password\": \"Dublin@18\" }")
                .filter(session)
                .when().post("rest/auth/1/session").then().log().all().extract().response().asString();

        // Adding a comment to the JIRA ticket
        String addcomment = "Jacob Manuel is trying to add a comment";
        String addCommentResponse = given().pathParam("id", "10004").log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        // "    \"body\": \"This is a comment from rest assured\",\n" +
                        "    \"body\": \"" + addcomment + "\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}")
                .filter(session)
                .when().post("rest/api/2/issue/{id}/comment")
                .then().assertThat().statusCode(201).log().all().extract().response().asString();
        JsonPath addComment = new JsonPath(addCommentResponse);
        String commentID = addComment.getString("id");

        // Adding an attachment to the JIRA ticket

        given().header("X-Atlassian-Token", "no-check")
                .header("Content-Type", "multipart/form-data")
                .log().all()
                .filter(session)
                .pathParam("id", "10004")
                .multiPart("file", new File("C:\\Users\\Jacob\\DemoProject_RestAssured\\src\\test\\java\\LibraryAPI\\Jiraattachment.txt"))
                .when().post("rest/api/2/issue/{id}/attachments")
                .then().log().all().assertThat().statusCode(200);

        // Get Issue

        String issueResponse = given().filter(session).log().all()
                .pathParam("id", "10004")
                .queryParam("fields", "comment")
                .when().get("rest/api/2/issue/{id}")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        // System.out.println("Get Issue Response is " + issueResponse);
        JsonPath checkComments = new JsonPath(issueResponse);
        int commentsSize = checkComments.getInt("fields.comment.comments.size()");

        //  System.out.println("Size of Comment Array is " + commentsSize);

        for (int i = 0; i < commentsSize; i++) {

            System.out.println("The comment id is " + checkComments.getString("fields.comment.comments[" + i + "].id"));

            String comid = checkComments.get("fields.comment.comments[" + i + "].id").toString();

            if (comid.equalsIgnoreCase(commentID)) {
                String message = checkComments.getString("fields.comment.comments[" + i + "].body");
                /*if (addcomment.equalsIgnoreCase(message)) {
                    System.out.println("The comment related to the comment id is " + message);
                }*/
                Assert.assertEquals(message,addcomment);

            }
        }

    }
}








