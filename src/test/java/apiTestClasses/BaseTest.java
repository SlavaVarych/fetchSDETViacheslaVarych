package apiTestClasses;

import apiCalls.GeoLocationApiCalls;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;

public class BaseTest {


    @BeforeTest(alwaysRun = true)
    public void clearAllureResult() throws IOException {
        Runtime.getRuntime().exec("rm -rf target/allure-results");
    }

    @BeforeTest(alwaysRun = true, dependsOnMethods = "clearAllureResult")
    public void clearResultFile() {
        GeoLocationApiCalls geoLocation = new GeoLocationApiCalls();
        geoLocation.clearFile("results/locationResultData.txt");
    }

    @BeforeTest(alwaysRun = true, dependsOnMethods = "clearResultFile")
    public void apiSetup() {
        RestAssured.filters(new AllureRestAssured());
    }

    @AfterSuite
    public void generateAllureReport() throws IOException {
        Runtime.getRuntime().exec("allure serve target/allure-results ");
    }

    @AfterTest
    public void testWithAttachment() throws IOException {
        String filePath = "results/locationResultData.txt";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Allure.addAttachment("My String Attachment", "text/plain", fileInputStream, ".txt");
    }
}
