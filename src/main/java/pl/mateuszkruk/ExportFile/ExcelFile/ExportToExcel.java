package pl.mateuszkruk.ExportFile.ExcelFile;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.Schedule.SumOfShifts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;


public class ExportToExcel {
    private final EmployeeListsMatcher employeeListsMatcher;

    public ExportToExcel(EmployeeListsMatcher employeeListsMatcher) {
        this.employeeListsMatcher = employeeListsMatcher;
    }

    public static void run(EmployeeListsMatcher employeeListsMatcher, Map<Integer, Map<Employee, Shifts>> finalSchedule,
                           Map<Employee, Integer> sumOfHours,
                           Map<Integer, SumOfShifts> dayAndNumberOfEmployees,
                           String fileName) {

        Rows rows = new Rows(employeeListsMatcher);
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Schedule Maker by Mateusz Kruk");

        Row headerRow = sheet.createRow(0);
        rows.createFirstRowAndColumns(headerRow);
        rows.createEmployeeRowsWithHours(sheet,finalSchedule,sumOfHours);
        rows.createDailySumOfEmployees(sheet,dayAndNumberOfEmployees);





        try (FileOutputStream export = new FileOutputStream(fileName +".xls")){
            workbook.write(export);
            workbook.close();

            System.out.println("Plik wyeksportowany poprwanie");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
