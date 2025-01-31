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

        removingSet(dayOfMonth,vacationAdder,employeeProposalFreeDays,sumOfMonthlyEmployeeHours,
                personalMonthlyStandardWorkingHours,employeesWithoutManagers);

        return employeesWithoutManagers;
    }

    public static List<Employee> managers(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                          VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours) {
        List<Employee> managers = new ArrayList<>(employeeListsMatcher.getManagerEmployees());

        removingSet(dayOfMonth,vacationAdder,employeeProposalFreeDays,sumOfMonthlyEmployeeHours,
        personalMonthlyStandardWorkingHours,managers);

        return managers;
    }

    public static List<Employee> creditEmployees(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                          VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours) {
        List<Employee> creditEmployees = new ArrayList<>(employeeListsMatcher.getCreditEmployees());

        removingSet(dayOfMonth,vacationAdder,employeeProposalFreeDays, sumOfMonthlyEmployeeHours,
                    personalMonthlyStandardWorkingHours,creditEmployees);

        return creditEmployees;
    }

    private static void removingSet(int day,VacationAdder vacationAdder,
                                    EmployeeProposalFreeDays employeeProposalFreeDays,
                                    SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                    PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                    List<Employee> employees){
        RemoveEmployeeVacation.remove(day,vacationAdder,employees);
        RemoveEmployeeProposalDay.remove(day,employees,employeeProposalFreeDays);
        RemoveEmployeeOverWorked.remove(employees,sumOfMonthlyEmployeeHours,personalMonthlyStandardWorkingHours);
    }
}
