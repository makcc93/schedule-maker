package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import pl.mateuszkruk.Employee.Employee;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorter {

    public static LinkedHashMap<Employee, Integer> sortedMapByHoursAscending (Map<Employee, Integer> map) {

        Preconditions.checkNotNull(map,"Argument cannot be null!");

        if (map.isEmpty()) {
            return new LinkedHashMap<>();
        }

        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a1, a2) -> a1, LinkedHashMap::new));
    }
}
