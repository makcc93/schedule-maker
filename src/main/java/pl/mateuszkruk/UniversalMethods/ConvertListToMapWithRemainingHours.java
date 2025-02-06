package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.List;
import java.util.Map;
@Component
public class ConvertListToMapWithRemainingHours {

    public static void convertListToMap(List<Employee> employees,
                                        Map<Employee, Integer> map,
                                        SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                        PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours){
        Preconditions.checkNotNull(employees,"List of employees cannot be null!");

        for (Employee employee : employees) {
            int remainingHours =
                                sumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee) -
                                personalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee);

            map.put(employee, remainingHours);
        }

    }
}