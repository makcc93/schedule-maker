package pl.mateuszkruk.WorkTime;

import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import java.util.HashMap;
import java.util.Map;
@Component
public class PersonalMonthlyStandardWorkingHours {
    EmployeeListsMatcher employeeListsMatcher;
    Map<Employee, Integer> personalMonthlyStandardWorkingHours = new HashMap<>();

    public PersonalMonthlyStandardWorkingHours(EmployeeListsMatcher employeeListsMatcher){
        this.employeeListsMatcher = employeeListsMatcher;
    }

    public void setPersonalMonthlyStandardWorkingHours(int numberOfHours){
        for (Employee employee : employeeListsMatcher.getAllEmployees()){
            personalMonthlyStandardWorkingHours.put(employee,numberOfHours);
        }
    }

    public void updatePersonalMonthlyStandardWorkingHours(Employee employee, int numberOfHours){
        personalMonthlyStandardWorkingHours.compute(employee, (key, value)
                -> (value == null ? 0 : value) - numberOfHours);

    }

    public Map<Employee, Integer> getPersonalMonthlyStandardWorkingHours() {
        return personalMonthlyStandardWorkingHours;
    }

    public int getPersonalMonthlyStandardWorkingHours (Employee employee){
        return personalMonthlyStandardWorkingHours.get(employee);
    }


}
