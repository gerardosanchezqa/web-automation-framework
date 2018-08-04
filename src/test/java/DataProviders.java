import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviders {

    String excelFileLocation = "C:\\Users\\Gera\\Desktop\\TestData.xlsx";

    @DataProvider(name="loginDataProvider")
    public Object[][] loginDataProvider(){
        return new Object[][]
                {
                        { "mngr145353", "yzAzyjy" }
                };

    }

    @DataProvider(name="InvalidLoginDataProvider")
    public Object[][] InvalidLoginDataProvider(){
        return new Object[][]
                {
                        { "invalidUserID", "yzAzyjy" }, // Invalid userID & valid password
                        { "mngr145353", "invalidPassword" }, // Valid userID & invalid password
                        { "invalidUserID", "invalidPassword" } // Invalid userID & invalid password
                };

    }

    @DataProvider(name="ValidLoginDataFromExcel")
    public Object[][] ValidLoginDataFromExcel() throws IOException, InvalidFormatException {
        return readExcelFile(excelFileLocation, "ValidLoginData");
    }

    @DataProvider(name="InvalidLoginDataFromExcel")
    public Object[][] InvalidLoginDataFromExcel() throws IOException, InvalidFormatException {
        return readExcelFile(excelFileLocation, "InvalidLoginData");
    }

    @DataProvider(name="ValidRegistrationData")
    public Object[][] ValidRegistrationData() throws IOException, InvalidFormatException {
        return readExcelFile(excelFileLocation, "ValidRegistrationData");
    }

    @DataProvider(name="ShippingInformation")
    public Object[][] ShippingInformation() throws IOException, InvalidFormatException {
        return readExcelFile(excelFileLocation, "ShippingInformation");
    }

    public Object[][] readExcelFile(String fileLocation, String sheetName) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(new File(fileLocation));
        Sheet worksheet;
        DataFormatter formatter= new DataFormatter();

        worksheet=wb.getSheet(sheetName);// get my sheet from workbook
        Row Row=worksheet.getRow(0);     //get my Row which start from 0

        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum
        int excelRowPointer;

        Object Data[][]= new Object[RowNum][ColNum]; // pass my  count data in array

        for(int i=0; i<RowNum; i++) //Loop work for Rows
        {

            Row row= worksheet.getRow(i);

            for (int j=0; j<ColNum; j++) //Loop work for colNum
            {
                if(row==null) {
                    Data[i][j] = "";
                }else
                {
                    Cell cell= row.getCell(j);
                    if(cell==null) {
                        Data[i][j] = ""; //if it get Null value it pass no data
                    }else
                    {
                        String value=formatter.formatCellValue(cell);
                        Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }
        }

        return Data;
    }
}