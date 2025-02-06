package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;

import java.util.*;

@Component
public class RemoveEmployeeWorkedManyDaysInARow {

    public static void remove(int dayOfMonth,
                              Map<Integer, Map<Employee, Shifts>> finalSchedule,
                              List<Employee> employees) {

        Preconditions.checkArgument(dayOfMonth > 0, "Day cannot be zero or less!");
        Preconditions.checkNotNull(finalSchedule, "Monthly Schedule cannot be null!");
        Preconditions.checkNotNull(employees, "List of employees cannot be null!");

        if (dayOfMonth > 4) {
            Iterator<Employee> iterator = employees.iterator();
            while (iterator.hasNext()) {
                Employee employee = iterator.next();

                if (workedLastDays(dayOfMonth,finalSchedule,employee)){
                    iterator.remove();
                }
            }
        }
    }


    private static boolean workedLastDays(int dayOfMonth,
                                   Map<Integer, Map<Employee, Shifts>> finalSchedule,
                                   Employee employee){
        for (int i = 1; i <= 4; i++){
            if (!finalSchedule.containsKey(dayOfMonth-i) ||
                !finalSchedule.get(dayOfMonth-i).containsKey(employee)){

                return false;
            }
        }

        return true;
    }

}
