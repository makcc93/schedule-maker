package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.WorkTime.SpecificShiftToEmployeeAdder;

import java.util.List;
import java.util.Map;

public class RemoveEmployeeSpecificShift {

    public static void remove(List<Employee> employees,
                              SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder,
                              int dayOfMonth){
        if (specificShiftToEmployeeAdder.getSpecificShifts().containsKey(dayOfMonth)){

                Map<Employee, Shifts> map = specificShiftToEmployeeAdder.getSpecificShifts().get(dayOfMonth);

                for (Map.Entry<Employee,Shifts> insideMap : map.entrySet()){
                    Employee employee = insideMap.getKey();

                    employees.remove(employee);
                }
        }
    }
}
