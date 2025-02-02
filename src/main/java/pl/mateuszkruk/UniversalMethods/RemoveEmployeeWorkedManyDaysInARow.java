package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveEmployeeWorkedManyDaysInARow {

    public static void remove(int dayOfMonth,
                              Map<Integer, Map<Employee, Shifts>> finalSchedule,
                              List<Employee> copyEmployeeList) {


        Preconditions.checkArgument(dayOfMonth > 0, "Day cannot be zero or less!");
        Preconditions.checkNotNull(finalSchedule, "Monthly Schedule cannot be null!");
        Preconditions.checkNotNull(copyEmployeeList, "List of employees cannot be null!");

        List<Employee> workingList = new ArrayList<>(copyEmployeeList);

        for (Employee employee : workingList) {
            if (    finalSchedule.get(dayOfMonth-1).containsKey(employee) &&
                    finalSchedule.get(dayOfMonth-2).containsKey(employee) &&
                    finalSchedule.get(dayOfMonth-3).containsKey(employee) &&
                    finalSchedule.get(dayOfMonth-4).containsKey(employee)){

                copyEmployeeList.remove(employee);
            }
        }
    }

}
