package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.UniversalMethods.ConvertListToMapWithRemainingHours;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConvertListToMapWithRemainingHoursTest {

@Test
    public void checkConvertionListToMapWithRemainingHours(){
    List<Employee> employees = List.of(
            new Employee("1","Adam", "Nowak",false,true),
            new Employee("2","Tomasz", "Wiśniewski",false,false),
            new Employee("3", "Dawid","Kowalski",true,false),
            new Employee("4","Wojciech", "Ptak", true,true));

    Map<Employee,Integer> returnedMap = new HashMap<>();

    SumOfMonthlyEmployeeHours mockSumOfMonthlyHours = mock(SumOfMonthlyEmployeeHours.class);
    PersonalMonthlyStandardWorkingHours mockPersonalMonthlyHours = mock(PersonalMonthlyStandardWorkingHours.class);

    Mockito.when(mockSumOfMonthlyHours.getSumOfEmployeeMonthlyHours(employees.get(0))).thenReturn(10);
    Mockito.when(mockSumOfMonthlyHours.getSumOfEmployeeMonthlyHours(employees.get(1))).thenReturn(20);
    Mockito.when(mockSumOfMonthlyHours.getSumOfEmployeeMonthlyHours(employees.get(2))).thenReturn(30);
    Mockito.when(mockSumOfMonthlyHours.getSumOfEmployeeMonthlyHours(employees.get(3))).thenReturn(40);

    Mockito.when(mockPersonalMonthlyHours.getPersonalMonthlyStandardWorkingHours(employees.get(0))).thenReturn(160);
    Mockito.when(mockPersonalMonthlyHours.getPersonalMonthlyStandardWorkingHours(employees.get(1))).thenReturn(160);
    Mockito.when(mockPersonalMonthlyHours.getPersonalMonthlyStandardWorkingHours(employees.get(2))).thenReturn(160);
    Mockito.when(mockPersonalMonthlyHours.getPersonalMonthlyStandardWorkingHours(employees.get(3))).thenReturn(160);

    ConvertListToMapWithRemainingHours.convertListToMap(employees,returnedMap,mockSumOfMonthlyHours,mockPersonalMonthlyHours);

    assertEquals(-150,returnedMap.get(employees.get(0)));
    assertEquals(-140,returnedMap.get(employees.get(1)));
    assertEquals(-130,returnedMap.get(employees.get(2)));
    assertEquals(-120,returnedMap.get(employees.get(3)));
}

@Test
    public void checkListOfEmployeesIsEmpty(){
    List<Employee> employees = List.of();

    Map<Employee,Integer> returnedMap = new HashMap<>();

    SumOfMonthlyEmployeeHours mockSumOfMonthlyHours = mock(SumOfMonthlyEmployeeHours.class);
    PersonalMonthlyStandardWorkingHours mockPersonalMonthlyHours = mock(PersonalMonthlyStandardWorkingHours.class);

    ConvertListToMapWithRemainingHours.convertListToMap(employees,returnedMap,mockSumOfMonthlyHours,mockPersonalMonthlyHours);
    assertTrue(returnedMap.isEmpty(),"Powinna być pusta");
}

@Test
    public void checkListOfEmployeesContainsNull(){
    List<Employee> employees = null;
    Map<Employee,Integer> returnedMap = new HashMap<>();

    SumOfMonthlyEmployeeHours mockSumOfMonthlyHours = mock(SumOfMonthlyEmployeeHours.class);
    PersonalMonthlyStandardWorkingHours mockPersonalMonthlyHours = mock(PersonalMonthlyStandardWorkingHours.class);

    assertThrows(IllegalArgumentException.class, () -> { ConvertListToMapWithRemainingHours.convertListToMap(employees,returnedMap,mockSumOfMonthlyHours,mockPersonalMonthlyHours);}, "Wyjątek dla null w liście pracowników");
}
}
