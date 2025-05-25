package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    // Method to read data from Excel and return it as a 2D array
    public static Object[][] readExcelData(String filePath) throws IOException {
        // Open the Excel file
        FileInputStream fileStream = new FileInputStream(filePath);
        Workbook excelBook = new XSSFWorkbook(fileStream);
        Sheet sheet = excelBook.getSheetAt(0); // Get the first sheet

        // Count rows (excluding header)
        int totalRows = sheet.getLastRowNum();

        // Count columns by checking the first row
        int totalCols = 0;
        Row header = sheet.getRow(0); // Get the header row
        if (header != null) {
            while (header.getCell(totalCols) != null) {
                totalCols++;
            }
        }

        // Create a 2D array to store the data
        Object[][] excelData = new Object[totalRows][totalCols];

        // Read data row by row, column by column
        for (int row = 1; row <= totalRows; row++) { // Start from 1 to skip the header
            Row currentRow = sheet.getRow(row); // Get the current row

            // Skip completely empty rows
            if (currentRow == null || isRowEmpty(currentRow)) {
                continue; // Skip to the next row
            }

            for (int col = 0; col < totalCols; col++) {
                Cell cell = currentRow.getCell(col); // Get the current cell
                if (cell == null) {
                    excelData[row - 1][col] = ""; // Handle empty cells
                } else {
                    excelData[row - 1][col] = cell.toString(); // Convert cell value to string
                }
            }
        }

        // Close workbook and input stream
        excelBook.close();
        fileStream.close();

        return excelData; // Return the 2D array
    }

    // Helper method to check if a row is completely empty
    private static boolean isRowEmpty(Row row) {
        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false; // Row has at least one non-empty cell
            }
        }
        return true; // Row is empty
    }
}
