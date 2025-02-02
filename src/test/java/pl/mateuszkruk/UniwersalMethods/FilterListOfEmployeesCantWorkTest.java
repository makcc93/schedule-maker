package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.UniversalMethods.FilterListOfEmployeesCantWork;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;
import pl.mateuszkruk.WorkTime.VacationAdder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FilterListOfEmployeesCantWorkTest {
    EmployeeListsMatcher employeeListsMatcher;
    SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;
    PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours;
    VacationAdder vacationAdder;
    EmployeeProposalFreeDays employeeProposalFreeDays;
    Employee employee1;
    Employee employee2;
    Employee employee3;
    List<Employee> employees = new ArrayList<>();
    Map<Integer, Map<Employee, Shifts>> schedule = new HashMap<>();

    @BeforeEach
    public void setup(){
        employeeListsMatcher = mock(EmployeeListsMatcher.class);
        sumOfMonthlyEmployeeHours = mock(SumOfMonthlyEmployeeHours.class);
        personalMonthlyStandardWorkingHours = mock(PersonalMonthlyStandardWorkingHours.class);
        vacationAdder = mock(VacationAdder.class);
        employeeProposalFreeDays = mock(EmployeeProposalFreeDays.class);
    }

    @Test
    public void filterManagers(){
        int day = 6;
        employee1 = new Employee("1","Tom", "Leg", false,true);
        employee2 = new Employee("2","Adam", "Smith", false,true);
        employee3 = new Employee("3","Jack", "Night", false,true);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        schedule.put(day-5,Map.of(employee1,Shifts.EIGHT_TO_TWENTY));

        when(employeeListsMatcher.getManagerEmployees()).thenReturn(employees);

        List<Employee> filteredManagers =
                FilterListOfEmployeesCantWork.managers(day,employeeListsMatcher,vacationAdder,employeeProposalFreeDays,sumOfMonthlyEmployeeHours,personalMonthlyStandardWorkingHours,schedule);

        assertFalse(filteredManagers.isEmpty());
        assertEquals(3,filteredManagers.size());

    }


}
