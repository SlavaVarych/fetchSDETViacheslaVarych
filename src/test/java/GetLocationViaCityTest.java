import apiTestClasses.ApiServicesTestClass;
import apiTestClasses.BaseTest;
import org.testng.annotations.Test;

public class GetLocationViaCityTest extends BaseTest {

    @Test
    public static void getLocationViaCityTest() {

        //Test data
        String filePath = "results/locationResultData.txt";
        String request = "New York, NY";

        ApiServicesTestClass apiServicesTestClass = new ApiServicesTestClass();

        apiServicesTestClass.getCityLocationAndWriteToFile(request, filePath);
    }
}
