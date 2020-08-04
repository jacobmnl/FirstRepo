package DenoProject;

import files.complexJSON;
import io.restassured.path.json.JsonPath;

public class complexJSONParser {

public static void main(String[] args){
    JsonPath js = new JsonPath(complexJSON.complexjson());

    // get the number of courses count. In the below line the number of elements in the array will be give . .size is used only in arrays
    int arraycount = js.getInt("courses.size");
    System.out.println("Number of cources are " + arraycount);


    // Purchase amount

    int purchaseAmount = js.getInt("dashboard.purchaseAmount");
    System.out.println("Purchase amount is   " + purchaseAmount);


    // Title of first course

    String firstTitle =    js.getString("courses[0].title");
    System.out.println("Title of fist book is   " + firstTitle);

    // All courses title and their respective prices


    for (int i=0; i <arraycount;i++ )
    {
        System.out.println(" Title of course " +i +" is"+ js.getString("courses["+i+"].title"));
        System.out.println(" Price of course " +i +" is"+ js.getInt  ("courses["+i+"].price"));
    }

    // No of copies sold where the title is RPA course

    for (int i=0;i<arraycount;i++)
    {
        String courseTitle = js.getString("courses["+i+"].title");
        if (courseTitle.equalsIgnoreCase("RPA"))
        {
            System.out.println("The number of copies sold for the RPA course is  " + js.getInt("courses["+i+"].copies"));
            break;
        }

    }

    // Verify sum of courses matches the total amount paid


 int totalAmount = 0;
    int coursePrice;

    for ( int i=0;i<arraycount;i++)
    {
        coursePrice =js.getInt("courses["+i+"].price");
        int copies = js.getInt("courses["+i+"].copies");
        totalAmount = (coursePrice * copies)+totalAmount;
    }
    if (totalAmount == purchaseAmount )
    {
        System.out.println(" Total Amount and purchase amount are same");
    }




}


}
