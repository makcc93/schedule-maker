package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.UniversalMethods.RemoveEmployeeWorkedManyDaysInARow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RemoveEmployeeWorkedManyDaysInARowTest {
    Map<Integer, Map<Employee, Shifts>> schedule = new HashMap<>();
    Employee employee1 = new Employee("1", "Adam", "Nowak", false, true);
    Employee employee2 = new Employee("2", "Tomasz", "Wiśniewski", false, false);
    Employee employee3 = new Employee("3", "Dawid", "Kowalski", true, false);

    List<Employee> employees = new ArrayList<>();

    @BeforeEach
    public void setup(){

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);
    }

    @Test
    public void removingWorkedLast4Days(){
        int day = 6;
        Map<Employee,Shifts> insideMap = new HashMap<>();
        Shifts allDay = Shifts.EIGHT_TO_TWENTY;
        insideMap.put(employee2,allDay);



        schedule.put(2,insideMap);
        schedule.put(3,insideMap);
        schedule.put(4,insideMap);
        schedule.put(5,insideMap);

        RemoveEmployeeWorkedManyDaysInARow.remove(day,schedule,employees);

        List<Employee> expectedList = List.of(employee1,employee3);

        assertEquals(expectedList,employees);

    }

    @Test
    public void emptyList(){
        int day = 12;

        employees = new ArrayList<>();
        schedule = new HashMap<>();

        RemoveEmployeeWorkedManyDaysInARow.remove(day,schedule,employees);

        assertTrue(employees.isEmpty());
    }

    @Test
    public void employeeIsNull(){
        int day = 6;

        Employee nullEmployee = null;
        employees.add(nullEmployee);
        Map<Employee,Shifts> insideMap = new HashMap<>();
        Shifts allDay = Shifts.EIGHT_TO_TWENTY;
        insideMap.put(nullEmployee,allDay);

        schedule.put(2,insideMap);
        schedule.put(3,insideMap);
        schedule.put(4,insideMap);
        schedule.put(5,insideMap);

        RemoveEmployeeWorkedManyDaysInARow.remove(day,schedule,employees);

        List<Employee> expectedList = List.of(employee1,employee2,employee3);

        assertEquals(expectedList,employees);
    }

    @Test
    public void dayIsZero(){
        int day = 0;
        Map<Employee,Shifts> insideMap = new HashMap<>();
        Shifts allDay = Shifts.EIGHT_TO_TWENTY;
        insideMap.put(employee2,allDay);



        schedule.put(2,insideMap);
        schedule.put(3,insideMap);
        schedule.put(4,insideMap);
        schedule.put(5,insideMap);


        assertThrows(IllegalArgumentException.class, () ->{RemoveEmployeeWorkedManyDaysInARow.remove(day,schedule,employees);});
    }
}
