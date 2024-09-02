package utils;

import java.util.Scanner;

public class ScannerInput {
    /**
     * This method allows input query from the terminal
     */
    public String userInputScanner() {
        Scanner scanner = new Scanner(System.in);
        String scannerString;

        System.out.print("Enter city name: ");
        scannerString = scanner.nextLine();
        scanner.close();
        System.out.println();
        return scannerString;
    }
}
