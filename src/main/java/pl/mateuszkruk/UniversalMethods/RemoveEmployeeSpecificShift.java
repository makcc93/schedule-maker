package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.WorkTime.SpecificShiftToEmployeeAdder;

import java.util.List;
import java.util.Map;
@Component
public class RemoveEmployeeSpecificShift {

    public static void remove(List<Employee> employees,
                              SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder,
                              int dayOfMonth){
        Preconditions.checkNotNull(employees,"List of employees cannot be null!");

        if (specificShiftToEmployeeAdder.getSpecificShiftsByDay().containsKey(dayOfMonth)){

                Map<Employee, Shifts> map = specificShiftToEmployeeAdder.getSpecificShiftsByDay().get(dayOfMonth);

                for (Map.Entry<Employee,Shifts> insideMap : map.entrySet()){
                    Employee employee = insideMap.getKey();

                    employees.remove(employee);
                }
        }
    }
}
