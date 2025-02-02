package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.UniversalMethods.PrepareSortConvertListToMap;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrepareSortConvertListToMapTest {
    LinkedHashMap<Employee,Integer> finalMap = new LinkedHashMap<>();
    List<Employee> employees = new ArrayList<>();
    Employee employee1;
    Employee employee2;
    Employee employee3;

    SumOfMonthlyEmployeeHours mockSumOfMonthlyEmployeeHours;
    PersonalMonthlyStandardWorkingHours mockPersonalMonthlyStandardWorkingHours;

    @BeforeEach
    public void setup(){
        employee1 = new Employee("1","Tom", "Leg", false,true);
        employee2 = new Employee("2","Adam", "Smith", true,false);
        employee3 = new Employee("3","Jack", "Night", false,true);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        mockPersonalMonthlyStandardWorkingHours = mock(PersonalMonthlyStandardWorkingHours.class);
        mockSumOfMonthlyEmployeeHours = mock(SumOfMonthlyEmployeeHours.class);
    }

    @Test
    public void sortAndConvertTest(){
        when(mockSumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee1)).thenReturn(180);
        when(mockSumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee2)).thenReturn(160);
        when(mockSumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee3)).thenReturn(200);

        when((mockPersonalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee1))).thenReturn(160);
        when((mockPersonalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee2))).thenReturn(160);
        when((mockPersonalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee3))).thenReturn(160);

        finalMap =
                PrepareSortConvertListToMap.run(employees,mockSumOfMonthlyEmployeeHours,mockPersonalMonthlyStandardWorkingHours);

        Map.Entry<Employee,Integer> entry = finalMap.entrySet().iterator().next();
            Employee firstEmployee = entry.getKey();
            int hours = entry.getValue();

            assertEquals(employee2,firstEmployee);
            assertEquals(0,hours);
    }

    @Test
    public void employeeIsNull(){
        Employee nullEmployee = null;
        employees.add(nullEmployee);
        when(mockSumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee1)).thenReturn(180);
        when(mockSumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee2)).thenReturn(160);
        when(mockSumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(employee3)).thenReturn(200);
        when(mockSumOfMonthlyEmployeeHours.getSumOfEmployeeMonthlyHours(nullEmployee)).thenReturn(200);

        when((mockPersonalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee1))).thenReturn(160);
        when((mockPersonalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee2))).thenReturn(160);
        when((mockPersonalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(employee3))).thenReturn(160);
        when((mockPersonalMonthlyStandardWorkingHours.getPersonalMonthlyStandardWorkingHours(nullEmployee))).thenReturn(160);

        finalMap =
                PrepareSortConvertListToMap.run(employees,mockSumOfMonthlyEmployeeHours,mockPersonalMonthlyStandardWorkingHours);

        Map.Entry<Employee,Integer> entry = finalMap.entrySet().iterator().next();
        Employee firstEmployee = entry.getKey();
        int hours = entry.getValue();

        assertEquals(employee2,firstEmployee);
        assertEquals(0,hours);


    }
}
