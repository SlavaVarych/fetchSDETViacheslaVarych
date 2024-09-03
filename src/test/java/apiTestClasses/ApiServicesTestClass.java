package apiTestClasses;

import apiCalls.GeoLocationApiCalls;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ApiServicesTestClass {

    GeoLocationApiCalls geoLocation;


    public ApiServicesTestClass() {
        geoLocation = new GeoLocationApiCalls();
    }

    @Step("{0}")
    public static void log(String message) {
        System.out.println(message);
    }

    public void getCityLocationAndWriteToFile(String request, String filePath) {
        log("Getting locations Data " + request);
        ArrayList<String> resultsList = geoLocation.getCityLocation(request);
        writeResultsToFileAndVerify(resultsList, filePath);
        verifyLinesInFile(filePath);
    }

    public void getZipLocationAndWriteToFile(String request, String filePath) {
        log("Getting locations Data: " + request);
        ArrayList<String> resultsList = geoLocation.getLocationViaZipCode(request);
        writeResultsToFileAndVerify(resultsList, filePath);
        verifyLinesInFile(filePath);
    }

    public void writeResultsToFileAndVerify(ArrayList<String> resultsList, String filePath) {
        log("Adding location results to the file");
        geoLocation.writeResultsDataToFile(resultsList);
        Path path = Paths.get(filePath);
        Assert.assertTrue(Files.exists(path), "Results file not found");
    }

    public void verifyLinesInFile(String filePath) {
        log("Verifying lines in the file");

        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            for (int i = 0; i < 3; i++) {
                String line = lines.get(i);

                if (i == 0) {
                    Assert.assertTrue(line.startsWith("Latitude"), "Latitude data is absent");
                } else if (i == 1) {
                    Assert.assertTrue(line.startsWith("Longitude"), "Longitude data is absent");

                } else if (i == 2) {
                    Assert.assertTrue(line.startsWith("City name"), "City name data is absent");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
