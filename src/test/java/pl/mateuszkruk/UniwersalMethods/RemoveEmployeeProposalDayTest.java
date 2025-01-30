package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.UniversalMethods.RemoveEmployeeProposalDay;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RemoveEmployeeProposalDayTest {
    List<Employee> employees = new ArrayList<>();
    Map<Employee,List<Integer>> proposalDays = new HashMap<>();
    EmployeeProposalFreeDays mockEmployeeProposal;
    Employee employee1;
    Employee employee2;
    Employee employee3;

    @BeforeEach
    public void setup(){
        mockEmployeeProposal = mock(EmployeeProposalFreeDays.class);

        employee1 = new Employee("1", "Adam", "Nowak", false, true);
        employee2 = new Employee("2", "Tomasz", "Wiśniewski", false, false);
        employee3 = new Employee("3", "Dawid", "Kowalski", true, false);
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        proposalDays.put(employee1,List.of(1,2,3));
        proposalDays.put(employee2,List.of(11,12,13));
        proposalDays.put(employee3,List.of(21,22,23));
    }

    @Test
    public void checkRemovingEmployees(){
        int dayOfMonth = 21;

        when(mockEmployeeProposal.getEmployeeProposalFreeDays()).thenReturn(proposalDays);

        RemoveEmployeeProposalDay.remove(dayOfMonth,employees,mockEmployeeProposal);

        List<Employee> expectedEmployees = List.of(employee1,employee2);

        assertEquals(expectedEmployees,employees);
    }

    @Test
    public void checkEmptyList(){
        int dayOfMonth = 21;
        when(mockEmployeeProposal.getEmployeeProposalFreeDays()).thenReturn(proposalDays);
        employees = new ArrayList<>();

        RemoveEmployeeProposalDay.remove(dayOfMonth,employees,mockEmployeeProposal);

        assertTrue(employees.isEmpty(), "lista pusta");

    }

    @Test
    public void checkEmptyProposalDays(){
        int dayOfMonth = 21;

        proposalDays.put(employee1,new ArrayList<>());
        proposalDays.put(employee2,new ArrayList<>());
        proposalDays.put(employee3,new ArrayList<>());

        when(mockEmployeeProposal.getEmployeeProposalFreeDays()).thenReturn(proposalDays);

        RemoveEmployeeProposalDay.remove(dayOfMonth,employees,mockEmployeeProposal);

        List<Employee> expectedFullList = List.of(employee1,employee2,employee3);

        assertEquals(expectedFullList,employees);
    }

    @Test
    public void checkEmployeeIsNull(){
        int dayOfMonth = 2;

        proposalDays.remove(employee1);
        proposalDays.remove(employee2);
        proposalDays.remove(employee3);
        proposalDays = new HashMap<>();

        proposalDays.put(employee1,List.of(1));
        proposalDays.put(null,List.of(2));
        proposalDays.put(employee3,List.of(3));

        when(mockEmployeeProposal.getEmployeeProposalFreeDays()).thenReturn(proposalDays);

        RemoveEmployeeProposalDay.remove(dayOfMonth,employees,mockEmployeeProposal);

        List<Employee> expectedList = List.of(employee1,employee2,employee3);

        assertEquals(expectedList,employees);
    }

    @Test
    public void employeeIsNull(){
        int day = 1;

        Employee nullEmployee = null;
        employees.add(nullEmployee);

        proposalDays.put(nullEmployee, List.of(1,2,3));

        when(mockEmployeeProposal.getEmployeeProposalFreeDays()).thenReturn(proposalDays);

        List<Employee> expectedList = List.of(employee2,employee3);

        RemoveEmployeeProposalDay.remove(day,employees,mockEmployeeProposal);

        assertEquals(expectedList,employees);
    }
}
