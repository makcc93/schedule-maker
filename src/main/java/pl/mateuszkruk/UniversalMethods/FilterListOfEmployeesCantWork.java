package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.WorkTime.*;

import java.util.ArrayList;
import java.util.List;

public class FilterListOfEmployeesCantWork {

    public static List<Employee> notManagers(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                             VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                             SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                             PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours) {

        List<Employee> employeesWithoutManagers = new ArrayList<>(employeeListsMatcher.getAllEmployeesWithoutManagers());
        RemoveEmployeeVacation.remove(dayOfMonth, vacationAdder, employeesWithoutManagers);
        RemoveEmployeeProposalDay.remove(dayOfMonth, employeesWithoutManagers, employeeProposalFreeDays);
        RemoveEmployeeOverWorked.remove(employeesWithoutManagers, employeeListsMatcher, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours);


        return employeesWithoutManagers;
    }

    public static List<Employee> managers(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                          VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours) {
        List<Employee> managers = new ArrayList<>(employeeListsMatcher.getManagerEmployees());
        RemoveEmployeeVacation.remove(dayOfMonth, vacationAdder, managers);
        RemoveEmployeeProposalDay.remove(dayOfMonth, managers, employeeProposalFreeDays);
        RemoveEmployeeOverWorked.remove(managers, employeeListsMatcher, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours);

        return managers;
    }

    public static List<Employee> creditEmployees(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                          VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours) {
        List<Employee> creditEmployees = new ArrayList<>(employeeListsMatcher.getCreditEmployees());
        RemoveEmployeeVacation.remove(dayOfMonth, vacationAdder, creditEmployees);
        RemoveEmployeeProposalDay.remove(dayOfMonth, creditEmployees, employeeProposalFreeDays);
        RemoveEmployeeOverWorked.remove(creditEmployees, employeeListsMatcher, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours);

        return creditEmployees;
    }
}
