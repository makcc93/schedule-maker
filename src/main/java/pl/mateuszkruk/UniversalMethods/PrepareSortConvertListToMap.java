package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
@Component
public class PrepareSortConvertListToMap {
    public static LinkedHashMap<Employee,Integer> run(List<Employee> employees,
                                                      SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                                      PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours){
        Preconditions.checkNotNull(employees, "List of employees cannot be null!");

        HashMap<Employee, Integer> employeesAndHours = new HashMap<>();
        ConvertListToMapWithRemainingHours.convertListToMap(employees,
                employeesAndHours, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours);

        return MapSorter.sortedMapByHoursAscending(employeesAndHours);
    }

}
