package pl.mateuszkruk.ScheduleGenerator;

import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.LinkedHashMap;
import java.util.Map;
@Component
public class MatchShiftWithEmployee {
    public static void run(Employee employeeWithLowestHours, Shifts shift,
                                        LinkedHashMap<Employee, Integer> lowestHoursWorkedEmployee,
                                              Map<Employee, Shifts> employeesForSingleDay,
                                              SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours){
        employeesForSingleDay.put(employeeWithLowestHours, shift);
        sumOfMonthlyEmployeeHours.addHours(employeeWithLowestHours, shift.getNumberOfHours());
        lowestHoursWorkedEmployee.remove(employeeWithLowestHours);
    }
}
