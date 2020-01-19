package bdd;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by sriramangajala on 02/12/16.
 */
public class JsonUtils {

    public static JSONObject getJsonObject()
    {
        try {
            return  (JSONObject)new JSONParser().parse("{\"name\":\"MyNode\", \"width\":200, \"height\":100}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
