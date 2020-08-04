package files;

import io.restassured.path.json.JsonPath;

public class reusableJSONPATH {

    public static  JsonPath rowtoJSON(String response)
    {

        JsonPath jspath = new JsonPath(response);
        return jspath;
    }

}
