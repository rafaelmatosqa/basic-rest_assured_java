package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    /**
     * Method to convert response String to Json
     */
    public static JsonPath rawToJson(String response)
    {
        JsonPath js1 = new JsonPath(response);
        return js1;
    }
}
