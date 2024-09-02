import utils.ScannerInput;

/**
 * This file runs program
 */
public class GeoLocationRun {

    public static void main(String[] args) {

        ScannerInput scannerInput = new ScannerInput();
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.clearFile("results/locationResultData.txt");
        geoLocation.getLocationData(scannerInput.userInputScanner());
    }
}
