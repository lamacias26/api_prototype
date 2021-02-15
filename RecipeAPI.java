/*
  Last Updated: 2-15-21
  Prints a recipe and its ready time.
  @author Leslie Macias Magana
 */

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeAPI {

    public static void getRecipeName() {
        // Create a HTTP Connection.
        String baseUrl = "https://api.spoonacular.com";
        String callAction = "/recipes/";
        String recipeId = "716429/";
        String apiKey = "581d0caffc2a4c6d847e00eb1879d02b";
        String urlString = baseUrl + callAction + recipeId + "information?apiKey=" + apiKey + "&includeNutrition=true";
        URL url;
        try {
            // Make the connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println("Error: Could not load " + status);
            } else {
                //Parsing input stream into a text string
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the connections.
                in.close();
                con.disconnect();
                //Print out our complete JSON string.
                System.out.println("Output: " + content.toString());
                //Parse that object into a usable Java JSON object.
                JSONObject obj = new JSONObject(content.toString());
                //Print out recipe name and its ready time.
                String recipeTitle = obj.getString("title");
                String readyTime = obj.getString("readyInMinutes");
                System.out.println("Recipe Name: " + recipeTitle + " \n" + "Ready in " + readyTime + " minutes.");
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex);
        }
    }
}

