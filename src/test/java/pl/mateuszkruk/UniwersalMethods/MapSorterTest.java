package pl.mateuszkruk.UniwersalMethods;

import org.apache.commons.collections4.map.LinkedMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.UniversalMethods.MapSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MapSorterTest {

    @Test
    public void sortingTest(){
        Map<Employee,Integer> map = new HashMap<>();
        Employee employee1 = new Employee("1", "Adam", "Nowak", false, true);
        Employee employee2 = new Employee("2", "Tomasz", "Wiśniewski", false, false);
        Employee employee3 = new Employee("3", "Dawid", "Kowalski", true, false);

        map.put(employee1,500);
        map.put(employee2,100);
        map.put(employee3,300);

       Map<Employee,Integer> sortedMap = MapSorter.sortedMapByHoursAscending(map);


        List<Map.Entry<Employee,Integer>> entries = new ArrayList<>(sortedMap.entrySet());

        assertEquals(300,entries.get(1).getValue());
        assertEquals(employee2, entries.get(0).getKey());

    }

    @Test
    public void checkEmptyMap(){
        Map<Employee,Integer> map = new HashMap<>();

        Map<Employee,Integer> sortedMap = MapSorter.sortedMapByHoursAscending(map);

        assertEquals(new HashMap<>(),sortedMap);
    }

    @Test
    public void checkNullMap(){
        Map<Employee,Integer> map = null;

        assertThrows(NullPointerException.class, () -> {Map<Employee,Integer> nullMap = MapSorter.sortedMapByHoursAscending(map);}, "Map is null!");
    }

    @Test
    public void checkSingleEmployeeIsNull(){
        Map<Employee,Integer> map = new HashMap<>();
        Employee employee1 = new Employee("1", "Adam", "Nowak", false, true);
        Employee employee2 = new Employee("2", "Tomasz", "Wiśniewski", false, false);
        Employee employee3 = null;

        map.put(employee1,300);
        map.put(employee2,200);
        map.put(employee3,100);

        Map<Employee,Integer> sortedMap = MapSorter.sortedMapByHoursAscending(map);

        assertEquals(map,sortedMap);

    }
}
