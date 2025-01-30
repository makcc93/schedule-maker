package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorter {

    public static LinkedHashMap<Employee, Integer> sortedMapByHoursAscending (Map<Employee, Integer> map){

        if (map.isEmpty()) {
            return new LinkedHashMap<>();
        }
        if (map == null){
            throw new NullPointerException("Map cannot be null!");
        }

        return map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                            (a1,a2) -> a1, LinkedHashMap::new));
    }

    public static LinkedHashMap<Employee,Integer> updateSorting (LinkedHashMap<Employee, Integer> map){
        if (map.isEmpty()) {
                return new LinkedHashMap<>();
        }
        if (map == null){
            throw new NullPointerException("Map cannot be null!");
        }

        return map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (a1,a2) -> a1, LinkedHashMap::new));
    }

}
