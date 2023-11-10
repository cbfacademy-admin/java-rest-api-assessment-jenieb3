package com.cbfacademy.apiassessment.utility;


import com.cbfacademy.apiassessment.model.Investment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportToExcel {
    public void export(List<Investment> investments, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Investments");

        // To create header row
        String[] headers = {"ID", "Name", "Quantity", "Purchase Price", "Current Price", "Returns"};
        Row headerRow = sheet.createRow(0);
        for (int i =0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
        // Fill data
        int rowNum = 1;
        for (Investment investment : investments) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(investment.getId());
            row.createCell(1).setCellValue(investment.getName());
            row.createCell(2).setCellValue(investment.getQuantity());
            row.createCell(3).setCellValue(investment.getPurchasePrice());
            row.createCell(4).setCellValue(investment.getCurrentPrice());
            row.createCell(5).setCellValue(investment.getReturns());
        }
        //Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        // Write the output to a file
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        }
        workbook.close();
    }
}