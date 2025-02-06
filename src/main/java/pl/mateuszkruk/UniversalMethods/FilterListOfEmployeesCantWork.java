package pl.mateuszkruk.UniversalMethods;

import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.WorkTime.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Component

public class FilterListOfEmployeesCantWork {

    public static List<Employee> notManagers(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                             VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                             SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                             PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                             Map<Integer, Map<Employee, Shifts>> schedule) {

        List<Employee> employeesWithoutManagers = new ArrayList<>(employeeListsMatcher.getAllEmployeesWithoutManagers());

        removingSet(dayOfMonth,vacationAdder,employeeProposalFreeDays,sumOfMonthlyEmployeeHours,
                personalMonthlyStandardWorkingHours,employeesWithoutManagers,schedule);

        return employeesWithoutManagers;
    }

    public static List<Employee> managers(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                          VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                          Map<Integer, Map<Employee, Shifts>> schedule) {
        List<Employee> managers = new ArrayList<>(employeeListsMatcher.getManagerEmployees());

        removingSet(dayOfMonth,vacationAdder,employeeProposalFreeDays,sumOfMonthlyEmployeeHours,
        personalMonthlyStandardWorkingHours,managers,schedule);

        return managers;
    }

    public static List<Employee> creditEmployees(int dayOfMonth, EmployeeListsMatcher employeeListsMatcher,
                                          VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                                 Map<Integer, Map<Employee, Shifts>> schedule) {
        List<Employee> creditEmployees = new ArrayList<>(employeeListsMatcher.getCreditEmployees());

        removingSet(dayOfMonth,vacationAdder,employeeProposalFreeDays, sumOfMonthlyEmployeeHours,
                    personalMonthlyStandardWorkingHours,creditEmployees,schedule);

        return creditEmployees;
    }

    private static void removingSet(int day,VacationAdder vacationAdder,
                                    EmployeeProposalFreeDays employeeProposalFreeDays,
                                    SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                    PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                    List<Employee> employees,
                                    Map<Integer, Map<Employee, Shifts>> schedule){

        RemoveEmployeeVacation.remove(day,vacationAdder,employees);
        RemoveEmployeeProposalDay.remove(day,employees,employeeProposalFreeDays);
        RemoveEmployeeOverWorked.remove(employees,sumOfMonthlyEmployeeHours,personalMonthlyStandardWorkingHours);
        RemoveEmployeeWorkedManyDaysInARow.remove(day,schedule,employees);
    }
}
