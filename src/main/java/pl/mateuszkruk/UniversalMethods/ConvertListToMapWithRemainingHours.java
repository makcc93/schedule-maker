package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.List;
import java.util.Map;

public class ConvertListToMapWithRemainingHours {

    public static void convertListToMap(List<Employee> employees,
                            Map<Employee, Integer> map,
                            SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                            PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours){

        if (employees == null) {
            throw new IllegalArgumentException("Lista pracowników nie może być null!");
        }

            for (Employee employee : employees) {
                int remainingHours =
                        sumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee) -
                                personalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee);

                map.put(employee, remainingHours);
            }

    }
}