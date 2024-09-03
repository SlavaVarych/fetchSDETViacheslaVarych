package apiCalls;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.NoDataException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;

public class GeoLocationApiCalls {

    private static final String HOST = "http://api.openweathermap.org/";

    private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
    private static final String GET_LOCATION_VIA_NAME_URL = "geo/1.0/direct?q=%s,%s,US&limit=5&appid=" + API_KEY;
    private static final String GET_LOCATION_VIA_ZIP_URL = "geo/1.0/zip?zip=%s,US&appid=" + API_KEY;

    /**
     * This method handles entered data
     */
    public void requestProcessing(String geoData) {

        String[] geoDataArr = geoData.split("\" \"");
        for (String data : geoDataArr) {
            boolean containsDigit = data.matches(".*\\d.*");
            if (containsDigit) {
                writeResultsDataToFile(getLocationViaZipCode(data));
            } else {
                writeResultsDataToFile(getCityLocation(data));
            }
        }
    }

    /**
     * This method gets data by City and State code
     */
    public ArrayList<String> getCityLocation(String geoData) {
        String[] geoDataArr = geoData.split(", ");

        RestAssured.baseURI = HOST;

        String request = "";
        try {
            request = format(GET_LOCATION_VIA_NAME_URL, geoDataArr[0], geoDataArr[1]);
        } catch (Exception e) {
            throw new NoDataException("Not enough data. Add State Code, please");
        }
        Response response = given()
                .get(request)
                .then()
                .log()
                .ifStatusCodeMatches(not(is(SC_OK)))
                .extract()
                .response();

        if (response.statusCode() != HttpStatus.SC_OK) {
            throw new AssertionError("Expected status code 200 but got " + response.statusCode() + ". Please check the request or endpoint.");
        }

        JSONArray jsonArray = new JSONArray(response.getBody().asPrettyString());
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonArray.get(0);
        } catch (JSONException e) {
            throw new NoDataException("There is no data available for request " + geoDataArr[0] + " " + geoDataArr[1] + ". Please try again with a different request.");
        }

        ArrayList<String> dataResult = new ArrayList<>();
        dataResult.add("Latitude is: " + jsonObject.get("lat"));
        dataResult.add("Longitude is: " + jsonObject.get("lon"));
        dataResult.add("City name is: " + jsonObject.get("name"));
        dataResult.add("State is: " + jsonObject.get("state"));
        dataResult.add("Country is: " + jsonObject.get("country"));
        dataResult.add("");

        dataResult.forEach(System.out::println);
        return dataResult;
    }

    /**
     * This method gets location data via Zip code
     */
    public ArrayList<String> getLocationViaZipCode(String geoData) {
        String request = format(GET_LOCATION_VIA_ZIP_URL, geoData);

        RestAssured.baseURI = HOST;

        Response response = given()
                .get(request)
                .then()
                .log()
                .ifStatusCodeMatches(not(is(SC_OK)))
                .extract()
                .response();

        if (response.statusCode() != HttpStatus.SC_OK) {
            throw new AssertionError("Expected status code 200 but got " + response.statusCode() + ". Please check the request or endpoint.");
        }

        response.getBody().jsonPath().get("lat");

        ArrayList<String> dataResult = new ArrayList<>();
        dataResult.add("Latitude is: " + response.jsonPath().get("lat").toString());
        dataResult.add("Longitude is: " + response.jsonPath().get("lon"));
        dataResult.add("City name is: " + response.jsonPath().get("name"));
        dataResult.add("Country is: " + response.jsonPath().get("country"));
        dataResult.add("Zip code is: " + response.jsonPath().get("zip"));
        dataResult.add("");

        dataResult.forEach(System.out::println);
        return dataResult;
    }

    /**
     * This method write results data to the file
     */
    public void writeResultsDataToFile(ArrayList<String> arrayList) {
        arrayList.forEach(line -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("results/locationResultData.txt", true))) {
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method clears file before adding data
     */
    public void clearFile(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



