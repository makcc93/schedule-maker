package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.UniversalMethods.RemoveEmployeeSpecificShift;
import pl.mateuszkruk.WorkTime.SpecificShiftToEmployeeAdder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveEmployeeSpecificShiftTest {
    List<Employee> employees = new ArrayList<>();
    Map<Integer, Map<Employee, Shifts>> employeeSpecificWorkDays = new HashMap<>();
    Map<Employee,Shifts> employeeSpecificShift = new HashMap<>();
    SpecificShiftToEmployeeAdder mockSpecificShiftAdder;
    Employee employee1;
    Employee employee2;
    Employee employee3;


    @BeforeEach
    public void setup(){
        employee1 = new Employee("1", "Adam", "Nowak", false, true);
        employee2 = new Employee("2", "Tomasz", "Wiśniewski", false, false);
        employee3 = new Employee("3", "Dawid", "Kowalski", true, false);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employeeSpecificShift.put(employee1,Shifts.EIGHT_TO_FOURTEEN);
        employeeSpecificShift.put(employee2,Shifts.EIGHT_TO_FOURTEEN);
        employeeSpecificShift.put(employee3,Shifts.EIGHT_TO_FOURTEEN);

        employeeSpecificWorkDays.put(1,employeeSpecificShift);
        employeeSpecificWorkDays.put(2,employeeSpecificShift);

        mockSpecificShiftAdder = mock(SpecificShiftToEmployeeAdder.class);
    }

    @Test
    public void checkRemoving(){
        int day = 22;

        when(mockSpecificShiftAdder.getSpecificShiftsByDay()).thenReturn(employeeSpecificWorkDays);

        RemoveEmployeeSpecificShift.remove(employees,mockSpecificShiftAdder,day);

        List<Employee> expected1List = List.of();

        List<Employee> expected2List = List.of(employee1,employee2,employee3);

        assertEquals(expected2List,employees);
    }

    @Test
    public void checkEmptyList(){
        int day = 22;

        employees = new ArrayList<>();

        when(mockSpecificShiftAdder.getSpecificShiftsByDay()).thenReturn(employeeSpecificWorkDays);

        RemoveEmployeeSpecificShift.remove(employees,mockSpecificShiftAdder,day);

        assertTrue(employees.isEmpty());
    }

    @Test
    public void checkNullEmployee(){
        int day = 22;

        Employee nullEmployee = null;
        employees.add(nullEmployee);

        when(mockSpecificShiftAdder.getSpecificShiftsByDay()).thenReturn(employeeSpecificWorkDays);

        assertDoesNotThrow(() -> {RemoveEmployeeSpecificShift.remove(employees,mockSpecificShiftAdder,day);},"do not throw a exeption");
    }

}
