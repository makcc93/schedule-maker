package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveEmployeeWorkedManyDaysInARow {

    public static void remove(int dayOfMonth,
                              Map<Integer, Map<Employee, Shifts>> finalSchedule,
                              List<Employee> copyEmployeeList) {
        int DaysInARow = 5;


        if (dayOfMonth > DaysInARow) {
            Map<Employee, Integer> whenEmployeeWorked = new HashMap<>();

            for (Map.Entry<Integer, Map<Employee, Shifts>> entry : finalSchedule.entrySet()) {
                Integer day = entry.getKey();
                Map<Employee, Shifts> employeeShift = entry.getValue();

                if (day > (dayOfMonth - (DaysInARow-1))) {
                    for (Map.Entry<Employee, Shifts> workingMap : employeeShift.entrySet()) {
                        Employee singleEmployee = workingMap.getKey();

                        whenEmployeeWorked.put(singleEmployee, whenEmployeeWorked.getOrDefault(singleEmployee, 0) + 1);
                    }
                }
            }

            for (Map.Entry<Employee, Integer> numberOfDaysEmployeeWorked : whenEmployeeWorked.entrySet()) {
                Employee employeeToRemove = numberOfDaysEmployeeWorked.getKey();

                if (numberOfDaysEmployeeWorked.getValue() == DaysInARow) {
                    copyEmployeeList.remove(employeeToRemove);
                }

            }
        }
    }
}
