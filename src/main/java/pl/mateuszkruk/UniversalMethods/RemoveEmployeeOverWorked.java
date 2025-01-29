package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.OverWorkingHours;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;


import java.util.Iterator;
import java.util.List;

public class RemoveEmployeeOverWorked {

    public static void remove(List<Employee> employees,
                              SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                              PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours
                              ){

        if (employees == null){
            throw new IllegalArgumentException("Employees is NULL!");
        }

        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()){
            Employee employee = iterator.next();
            int sumOfWorkedHours = sumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee);
            int monthlyHoursToWork = personalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee);
            int possibleOverWorkingHours = OverWorkingHours.OVER_WORKING_HOURS.getNumberOfOverWorkingHoursPerEmployee();

                if (sumOfWorkedHours > (monthlyHoursToWork + possibleOverWorkingHours)) {
                    iterator.remove();
                }
        }
    }
}
