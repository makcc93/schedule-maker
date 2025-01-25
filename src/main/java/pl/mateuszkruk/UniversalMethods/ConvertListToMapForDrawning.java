package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.List;
import java.util.Map;

public class ConvertListToMapForDrawning {

    public static void convertListToMap(List<Employee> employees,
                            Map<Employee, Integer> map,
                            SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                            PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours){
        for (Employee employee : employees){
            int percentageOfEmployeeUsageMontlyHours =
                    sumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee) -
                    personalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee);

            map.put(employee,percentageOfEmployeeUsageMontlyHours);
        }
    }
}
