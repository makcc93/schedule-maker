package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.VacationAdder;

import java.util.List;
import java.util.Map;

public class RemoveEmployeeVacation {

    public static void remove (int dayOfMonth,
                               VacationAdder vacationAdder,
                               List<Employee> employees){
        for(Map.Entry<Employee, List<Integer>> entry : vacationAdder.getEmployeesVacations().entrySet()){
            Employee employee = entry.getKey();
            List<Integer> day = entry.getValue();

            if (day.contains(dayOfMonth)){
                employees.remove(employee);

            }
        }
    }
}
