package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;

import java.util.List;
import java.util.Map;

public class RemoveEmployeeProposalDay {

    public static void remove(int dayOfMonth,
                              List<Employee> employees,
                              EmployeeProposalFreeDays employeeProposalFreeDays){
        Preconditions.checkNotNull(employees,"List of employees cannot be null!");
        Preconditions.checkArgument(dayOfMonth > 0, "Day cannot be zero or less!");

        for (Map.Entry<Employee, List<Integer>> entry : employeeProposalFreeDays.getEmployeeProposalFreeDays().entrySet()){
            Employee employee = entry.getKey();
            List<Integer> days = entry.getValue();

            for (int day : days){
                if (day == dayOfMonth){
                    employees.remove(employee);
                }
            }
        }
    }
}
