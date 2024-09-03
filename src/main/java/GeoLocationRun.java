import apiCalls.GeoLocationApiCalls;
import utils.ScannerInput;

/**
 * This file runs program
 */
public class GeoLocationRun {

    public static void main(String[] args) {

        ScannerInput scannerInput = new ScannerInput();
        GeoLocationApiCalls geoLocation = new GeoLocationApiCalls();
        geoLocation.clearFile("results/locationResultData.txt");
        geoLocation.requestProcessing(scannerInput.userInputScanner());
    }
}
