package pl.mateuszkruk.WorkTime;
import org.springframework.stereotype.Service;
import pl.mateuszkruk.Employee.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeProposalFreeDays {
    Map<Employee, List<Integer>> employeeProposalFreeDays = new HashMap<>();


    public void addEmployeeProposalFreeDay(Employee employee, int day){
        employeeProposalFreeDays.putIfAbsent(employee, new ArrayList<>());
        employeeProposalFreeDays.get(employee).add(day);
    }

    public Map<Employee, List<Integer>> getEmployeeProposalFreeDays() {
        return employeeProposalFreeDays;
    }

}
