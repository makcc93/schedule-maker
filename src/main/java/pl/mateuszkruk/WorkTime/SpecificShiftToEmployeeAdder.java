package pl.mateuszkruk.WorkTime;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;

import java.util.HashMap;
import java.util.Map;

public class SpecificShiftToEmployeeAdder {
    Map<Integer, Map<Employee,Shifts>> specificShifts = new HashMap<>();

    public void add(Employee employee, Shifts shifts, int dayOfMonth){
        Map<Employee,Shifts> employeeShift = specificShifts.getOrDefault(dayOfMonth,new HashMap<>());

        employeeShift.put(employee,shifts);

        specificShifts.put(dayOfMonth,employeeShift);
    }

    public Map<Integer, Map<Employee, Shifts>> getSpecificShiftsByDay() {
        return specificShifts;
    }
}
