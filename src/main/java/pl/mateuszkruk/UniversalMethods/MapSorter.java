package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorter {
    private SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;

    public static LinkedHashMap<Employee, Integer> sortedMapByHoursAscending (Map<Employee, Integer> map){

        if (map.isEmpty() || map == null) {
            return new LinkedHashMap<>();
        }

        return map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                            (a1,a2) -> a1, LinkedHashMap::new));
        }

        public static LinkedHashMap<Employee,Integer> updateSorting (LinkedHashMap<Employee, Integer> map){
            if (map.isEmpty() || map == null) {
                return new LinkedHashMap<>();
            }

            return map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (a1,a2) -> a1, LinkedHashMap::new));
        }
}
