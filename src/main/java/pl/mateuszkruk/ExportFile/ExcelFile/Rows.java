package pl.mateuszkruk.ExportFile.ExcelFile;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.Schedule.SumOfShifts;

import java.util.Map;

public class Rows {
private final EmployeeListsMatcher employeeListsMatcher;

    public Rows(EmployeeListsMatcher employeeListsMatcher) {
        this.employeeListsMatcher = employeeListsMatcher;
    }

    protected void createFirstRowAndColumns(Row row){
        row.createCell(0).setCellValue("IMIE NAZWISKO");

        for (int day = 1 ; day <=31 ; day++) {
            row.createCell(day).setCellValue(day);
        }

        row.createCell(32).setCellValue("SUMA GODZIN");
    }

    protected void createEmployeeRowsWithHours(Sheet sheet,
                                               Map<Integer, Map<Employee, Shifts>> finalSchedule,
                                               Map<Employee, Integer> sumOfHours){
        int size = employeeListsMatcher.getAllEmployees().size();
        int positionOfCellWithSumOfEmployeeHours = 32;

        for (int i = 1; i <= size; i++) {
            Employee employee = employeeListsMatcher.getAllEmployees().get(i - 1);

            sheet.createRow(i)
                    .createCell(0)
                    .setCellValue(String.valueOf(employee));

            for (int day = 1; day <= 31; day++){
                if (finalSchedule.containsKey(day) && finalSchedule.get(day).containsKey(employee)){
                    sheet.getRow(i).createCell(day).setCellValue(finalSchedule.get(day).get(employee).getHoursShift());
                }
            }

            if (sumOfHours.get(employee) != null){
                sheet.getRow(i).createCell(positionOfCellWithSumOfEmployeeHours).setCellValue(sumOfHours.get(employee));
            }
        }
    }

    protected void createDailySumOfEmployees(Sheet sheet,
                                             Map<Integer, SumOfShifts> dayAndNumberOfEmployees){
        int firstRowUnderEmployees = employeeListsMatcher.getAllEmployees().size()+1;
        int secondRowUnderEmployees = employeeListsMatcher.getAllEmployees().size()+2;

        sheet.createRow(firstRowUnderEmployees).createCell(0).setCellValue("OBSADA RANO");
        for (int day = 1; day <= 31;day++){

            if (dayAndNumberOfEmployees.get(day) != null) {
                int sumOfMorningEmployees = dayAndNumberOfEmployees.get(day).getSumOfMorningEmployees();

                sheet.getRow(firstRowUnderEmployees).createCell(day).setCellValue(sumOfMorningEmployees);
            }
        }


        sheet.createRow(secondRowUnderEmployees).createCell(0).setCellValue("OBSADA POPOŁUDNIU");
        for (int day = 1; day <= 31;day++){

            if (dayAndNumberOfEmployees.get(day) != null) {
                int sumOfAfternoonEmployees = dayAndNumberOfEmployees.get(day).getSumOfAfternoonEmployees();

                sheet.getRow(secondRowUnderEmployees).createCell(day).setCellValue(sumOfAfternoonEmployees);
            }
        }
    }
}
