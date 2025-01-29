package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.UniversalMethods.RemoveEmployeeOverWorked;
import pl.mateuszkruk.WorkTime.OverWorkingHours;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveEmployeeOverWorkedTest {
    SumOfMonthlyEmployeeHours mockSumOfMonthlyHours;
    PersonalMonthlyStandardWorkingHours mockPersonalMonthlyStandard;

    @BeforeEach
    public void setup(){
        mockSumOfMonthlyHours = mock(SumOfMonthlyEmployeeHours.class);
        mockPersonalMonthlyStandard = mock(PersonalMonthlyStandardWorkingHours.class);
        OverWorkingHours.OVER_WORKING_HOURS.setNumberOfOverWorkingHoursPerEmployee(10);
    }

    @Test
    public void checkRemovingEmployee(){
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee("1", "Adam", "Nowak", false, true);
        Employee employee2 = new Employee("2", "Tomasz", "Wiśniewski", false, false);
        Employee employee3 = new Employee("3", "Dawid", "Kowalski", true, false);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);



        when(mockSumOfMonthlyHours.getSumOfEmployeeMonthlyHours(employee1)).thenReturn(100);
        when(mockSumOfMonthlyHours.getSumOfEmployeeMonthlyHours(employee2)).thenReturn(100);
        when(mockSumOfMonthlyHours.getSumOfEmployeeMonthlyHours(employee3)).thenReturn(200);

        OverWorkingHours.OVER_WORKING_HOURS.setNumberOfOverWorkingHoursPerEmployee(20);

        when(mockPersonalMonthlyStandard.getPersonalMonthlyStandardWorkingHours(employee1)).thenReturn(160);
        when(mockPersonalMonthlyStandard.getPersonalMonthlyStandardWorkingHours(employee2)).thenReturn(160);
        when(mockPersonalMonthlyStandard.getPersonalMonthlyStandardWorkingHours(employee3)).thenReturn(160);



        RemoveEmployeeOverWorked.remove(employees, mockSumOfMonthlyHours,mockPersonalMonthlyStandard);

       boolean testEmployee1 = employees.contains(employee1);
       boolean testEmployee2 = employees.contains(employee2);
       boolean testEmployee3 = employees.contains(employee3);

        assertTrue(testEmployee1);
        assertTrue(testEmployee2);
        assertFalse(testEmployee3);
    }

    @Test
    public void listIsNULL(){
        List<Employee> employees = null;

        assertThrows(IllegalArgumentException.class, () ->{ RemoveEmployeeOverWorked.remove(employees,mockSumOfMonthlyHours,mockPersonalMonthlyStandard); },"lista jest nullem");

    }
}
