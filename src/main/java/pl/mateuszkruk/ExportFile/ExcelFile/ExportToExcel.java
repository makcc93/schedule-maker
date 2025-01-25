package pl.mateuszkruk.ExportFile.ExcelFile;

import pl.mateuszkruk.ExportFile.ExcelFile.Rows;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExportToExcel {

    public void run() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Schedule Maker by Mateusz Kruk");

        Row headerRow = sheet.createRow(0);
        Rows.createFirstRow(headerRow);

        Row dataRow1



        try (FileOutputStream export = new FileOutputStream("testowyEXCEL.xls")){
            workbook.write(export);
            workbook.close();

            System.out.println("Plik wyeksportowany poprwanie");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
