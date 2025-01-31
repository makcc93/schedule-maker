package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.UniversalMethods.RemoveEmployeeVacation;
import pl.mateuszkruk.WorkTime.VacationAdder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveEmployeeVacationTest {
    VacationAdder mockVacationAdder;
    Employee employee1;
    Employee employee2;
    Employee employee3;
    List<Employee> employees = new ArrayList<>();
    Map<Employee, List<Integer>> map = new HashMap<>();

    @BeforeEach
    public void setup(){
        mockVacationAdder = mock(VacationAdder.class);

        employee1 = new Employee("1", "Adam", "Nowak", false, true);
        employee2 = new Employee("2", "Tomasz", "Wiśniewski", false, false);
        employee3 = new Employee("3", "Dawid", "Kowalski", true, false);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        map.put(employee1,List.of(1,2,3));
        map.put(employee2, List.of(11,12,13));
        map.put(employee3, List.of(21,22,23));
    }

    @Test
    public void checkRemovingEmployees(){
        int day = 21;

        when(mockVacationAdder.getEmployeesVacations()).thenReturn(map);

        RemoveEmployeeVacation.remove(day,mockVacationAdder,employees);

        List<Employee> expectedList = List.of(employee1,employee2);

        assertEquals(expectedList,employees);
    }

    @Test
    public void checkEmptyListOfEmployees(){
        int day = 1;

        employees = new ArrayList<>();
        map = new HashMap<>();

        when(mockVacationAdder.getEmployeesVacations()).thenReturn(map);

        RemoveEmployeeVacation.remove(day,mockVacationAdder,employees);

        assertTrue(employees.isEmpty());
        verify(mockVacationAdder,times(1)).getEmployeesVacations();
    }

    @Test
    public void noneEmployeeHasThatDay(){
        int day = 17;

        when(mockVacationAdder.getEmployeesVacations()).thenReturn(map);

        RemoveEmployeeVacation.remove(day,mockVacationAdder,employees);

        List<Employee> expectedList = List.of(employee1,employee2,employee3);

        assertEquals(expectedList,employees);
    }

    @Test
    public void singleEmployeeIsNull(){
        int day = 1;

        Employee nullEmployee = null;
        map.put(nullEmployee,List.of(1));

        when(mockVacationAdder.getEmployeesVacations()).thenReturn(map);
        
        RemoveEmployeeVacation.remove(day,mockVacationAdder,employees);

        verify(mockVacationAdder,times(1)).getEmployeesVacations();
    }
}
