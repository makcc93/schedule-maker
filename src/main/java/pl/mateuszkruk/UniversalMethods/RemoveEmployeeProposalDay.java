package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;

import java.util.List;
import java.util.Map;

public class RemoveEmployeeProposalDay {

    public static void remove(int dayOfMonth,
                              List<Employee> employees,
                              EmployeeProposalFreeDays employeeProposalFreeDays){

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
