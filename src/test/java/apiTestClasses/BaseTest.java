package apiTestClasses;

import apiCalls.GeoLocationApiCalls;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class BaseTest {


    @BeforeTest(alwaysRun = true)
    public void clearAllureResult() throws IOException {
        Runtime.getRuntime().exec("rm -rf allure-results");
    }

    @BeforeTest(alwaysRun = true, dependsOnMethods = "clearAllureResult")
    public void clearResultFile() {
        GeoLocationApiCalls geoLocation = new GeoLocationApiCalls();
        geoLocation.clearFile("results/locationResultData.txt");
    }

    @AfterSuite()
    public void generateAllureReport() throws IOException {
        Runtime.getRuntime().exec("allure serve allure-results ");
    }
}
