package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static ExcelTestData[] readTestData(String excelFilePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            int rowCount = sheet.getLastRowNum();
            ExcelTestData[] testData = new ExcelTestData[rowCount];

            int i = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                String SearchItem = getStringValue(row.getCell(0));
                String subtotal = getStringValue(row.getCell(1));
                testData[i++] = new ExcelTestData(SearchItem,subtotal);
            }

            return testData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return null;
        }
    }

}
