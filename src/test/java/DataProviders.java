import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="loginDataProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "mngr145353", "yzAzyjy" }
                };

    }

}