package pl.mateuszkruk.Employee;

import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class EmployeeListsMatcher {
    Map<String, Employee> allEmployees = new HashMap<>();
    Map<String, Employee> allEmployeesWithoutManagers = new HashMap<>();
    Map<String, Employee> creditEmployees = new HashMap<>();
    Map<String, Employee> managerEmployees = new HashMap<>();

    public void addEmployeeToList(Employee employee) {
        allEmployees.put(employee.getSapNumber(), employee);
        allEmployeesWithoutManagers.put(employee.getSapNumber(), employee);

        if (employee.canOperateCredit){
            creditEmployees.put(employee.getSapNumber(), employee);
        }

        if (employee.isManager){
            managerEmployees.put(employee.getSapNumber(), employee);
            allEmployeesWithoutManagers.remove(employee.getSapNumber(), employee);
        }
    }

    public void removeEmployeeFromList(Employee employee){
        allEmployees.remove(employee.getSapNumber(),employee);

        if (employee.isManager){
            managerEmployees.remove(employee.getSapNumber(),employee);
        }
        if (employee.canOperateCredit){
            creditEmployees.remove(employee.getSapNumber(),employee);
        }
        if (!employee.isManager){
        allEmployeesWithoutManagers.remove(employee.getSapNumber(),employee);
        }

    }

    public Employee getEmployee(String sapNumber){
        return allEmployees.get(sapNumber);
    }

    public List<Employee> getCreditEmployees(){
        return new ArrayList<>(creditEmployees.values());
    }

    public List<Employee> getManagerEmployees(){
        return new ArrayList<>(managerEmployees.values());
    }

    public List<Employee> getAllEmployees(){
        return new ArrayList<>(allEmployees.values());
    }

    public List<Employee> getAllEmployeesWithoutManagers(){
        return new ArrayList<>(allEmployeesWithoutManagers.values());
    }

}
