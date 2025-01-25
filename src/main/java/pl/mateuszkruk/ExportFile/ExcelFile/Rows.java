package pl.mateuszkruk.ExportFile.ExcelFile;

import org.apache.poi.ss.usermodel.Row;

public class Rows {

    protected static void createFirstRow(Row row){
        row.createCell(0).setCellValue("IMIE NAZWISKO");

        for (int day = 1 ; day <=31 ; day++) {
            row.createCell(day).setCellValue(day);
        }

        row.createCell(32).setCellValue("SUMA GODZIN");
    }

}
