package com.portfolio.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProvider {

    private static final String FILE_PATH = 
        "src/test/resources/testdata/users.xlsx";

    public static Object[][] getUserData() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            Workbook workbook   = new XSSFWorkbook(fis);
            Sheet sheet         = workbook.getSheetAt(0); // first sheet

            // Total rows minus 1 (exclude header row)
            int totalRows = sheet.getPhysicalNumberOfRows() - 1;
            Object[][] data = new Object[totalRows][1];

            for (int i = 1; i <= totalRows; i++) { // start from 1 to skip header
                Row row = sheet.getRow(i);

                String name   = row.getCell(0).getStringCellValue();
                String email  = row.getCell(1).getStringCellValue()
                                .replace("@", 
                                System.currentTimeMillis() + "@"); // unique email
                String gender = row.getCell(2).getStringCellValue();
                String status = row.getCell(3).getStringCellValue();

                data[i - 1][0] = new String[]{name, email, gender, status};
            }

            workbook.close();
            fis.close();
            return data;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read users.xlsx: " + e.getMessage());
        }
    }
}