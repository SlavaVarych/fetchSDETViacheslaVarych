import apiTestClasses.ApiServicesTestClass;
import apiTestClasses.BaseTest;
import org.testng.annotations.Test;

public class GetLocationViaZipCodeTest extends BaseTest {
    @Test
    public void getLocationViaZipCodeTest() {

        //Test data
        String filePath = "results/locationResultData.txt";
        String request = "60630";

        ApiServicesTestClass apiServicesTestClass = new ApiServicesTestClass();

        apiServicesTestClass.getZipLocationAndWriteToFile(request, filePath);
    }
}
