package pl.mateuszkruk.WorkTime;

import pl.mateuszkruk.Employee.Employee;

import java.util.Map;
import java.util.HashMap;

public class SumOfMonthlyEmployeeHours {
    Map<Employee, Integer> sumOfEmployeeMonthlyHours = new HashMap<>();

    public void addHours(Employee employee, int shiftHours){
        sumOfEmployeeMonthlyHours.put(employee, sumOfEmployeeMonthlyHours.getOrDefault(employee,0)+shiftHours);
    }

    public void removeHours(Employee employee, int shiftHours){
        sumOfEmployeeMonthlyHours.put(employee, sumOfEmployeeMonthlyHours.getOrDefault(employee,0)-shiftHours);
    }

    public Integer getSumOfEmployeeMonthlyHours(Employee employee) {
        return sumOfEmployeeMonthlyHours.getOrDefault(employee,0);
    }

    public Map<Employee, Integer> getMapOfAllEmployeesHours(){
        return sumOfEmployeeMonthlyHours;
    }


}
