package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.WorkTime.OverWorkingHours;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.List;

public class RemoveEmployeeOverWorked {

    public static void remove(List<Employee> employees,
                              EmployeeListsMatcher employeeListsMatcher,
                              SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                              PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours
                              ){
        for (Employee employee : employeeListsMatcher.getAllEmployees()){
            if (sumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee)
                                                >
                (personalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee)
                                                +
                 OverWorkingHours.OVER_WORKING_HOURS.getNumberOfOverWorkingHoursPerEmployee())) {
                employees.remove(employee);


            }
        }

    }
}
