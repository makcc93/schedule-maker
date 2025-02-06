package pl.mateuszkruk.ExportFile;
import org.springframework.stereotype.Service;
import pl.mateuszkruk.Schedule.SumOfShifts;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.Employee.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
@Service
public class ExportToCSV {

    public static void exportScheduleToCSV(Map<Integer, Map<Employee, Shifts>> schedule,
                                           List<Employee> employees, Map<Employee, Integer> sumOfHours,
                                           Map<Integer, SumOfShifts> dayAndNumberOfEmployees, String fileName){
        try (FileWriter writer = new FileWriter(fileName)){
            writer.write("Pracownik");
            for (int day = 1; day <= 31; day++){
                writer.write("," + day);
            }
            writer.write(","+"Suma godzin");
            writer.write("\n");

            for (Employee employee : employees){
                writer.write(employee.getFullName());
                for (int day = 1; day <= 31; day++){
                    String hours = "";

                    if (schedule.containsKey(day) && schedule.get(day).containsKey(employee)){
                        hours = schedule.get(day).get(employee).getHoursShift();
                    }
                    writer.write("," +(hours.isEmpty() ? "" : hours));

                }
                writer.write(","+sumOfHours.get(employee));
                writer.write("\n");
            }

            writer.write("Liczba pracowników rano");
            for (int day = 1; day <= 31; day++){
                SumOfShifts sumOfShifts = dayAndNumberOfEmployees.get(day);
                if (sumOfShifts == null){
                    writer.write(",0");
                }
                else {
                    writer.write("," + dayAndNumberOfEmployees.get(day).getSumOfMorningEmployees());
                }
            }

            writer.write("\n");

            writer.write("Liczba pracowników popołudniu");
            for (int day = 1; day <= 31; day++){
                SumOfShifts sumOfShifts = dayAndNumberOfEmployees.get(day);

                if (sumOfShifts == null){
                    writer.write(",0");
                }
                else {
                    writer.write("," + dayAndNumberOfEmployees.get(day).getSumOfAfternoonEmployees());
                }
            }

            System.out.println("Plik o nazwie "+fileName+" został wygenerowany poprawnie!");
        }
        catch (IOException e) {
            System.err.println("Błąd podczas zapisywania pliku: " + e.getMessage());
        }
    }
}
