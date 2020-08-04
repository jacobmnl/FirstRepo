package DenoProject;

import files.complexJSON;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sumValidation {

    @Test
    public void sumOfCourses()
    {
        JsonPath js = new JsonPath(complexJSON.complexjson());
        int arraycount = js.getInt("courses.size");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");

        int totalAmount = 0;
        int coursePrice;

        for ( int i=0;i<arraycount;i++)
        {
            coursePrice =js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            totalAmount = (coursePrice * copies)+totalAmount;
        }
        Assert.assertEquals(totalAmount,purchaseAmount);

    }


}
