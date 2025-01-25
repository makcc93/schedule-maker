package pl.mateuszkruk.ScheduleGenerator;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.WorkTime.SpecificShiftToEmployeeAdder;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;
import java.util.LinkedHashMap;
import java.util.Map;

public class DrawSpecificShifts {
    private final SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder;
    private final SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;
    private final EmployeeListsMatcher employeeListsMatcher;
    int sumOfMorningShifts;
    int sumOfAfternoonShifts;

    public DrawSpecificShifts(SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder,
                              SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                              EmployeeListsMatcher employeeListsMatcher){
        this.specificShiftToEmployeeAdder = specificShiftToEmployeeAdder;
        this.sumOfMonthlyEmployeeHours = sumOfMonthlyEmployeeHours;
        this.employeeListsMatcher = employeeListsMatcher;
    }

    public void draw(Map<Employee, Shifts> employeesForSingleDay,
                     LinkedHashMap<Employee,Integer> lowestHoursWorkedEmployees,
                     SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                     int dayOfMonth){
            sumOfMorningShifts = 0;
            sumOfAfternoonShifts = 0;

            if (specificShiftToEmployeeAdder.getSpecificShifts().containsKey(dayOfMonth)) {

                    Map<Employee, Shifts> employeeShift = specificShiftToEmployeeAdder.getSpecificShifts().get(dayOfMonth);

                    for (Map.Entry<Employee, Shifts> map : employeeShift.entrySet()) {
                        Employee employee = map.getKey();
                        Shifts shift = map.getValue();

                        employeesForSingleDay.put(employee, shift);
                        sumOfMonthlyEmployeeHours.addHours(employee,shift.getNumberOfHours());
                        lowestHoursWorkedEmployees.remove(employee);


                            if (shift.isMorningShift() && !employeeListsMatcher.getManagerEmployees().contains(employee)){
                                sumOfMorningShifts++;
                            }
                            if (shift.isAfternoonShift() && !employeeListsMatcher.getManagerEmployees().contains(employee)){
                                sumOfAfternoonShifts++;
                            }

                    break;
                }
            }
    }

    public int getSumOfMorningShifts() {
        return sumOfMorningShifts;
    }

    public int getSumOfAfternoonShifts() {
        return sumOfAfternoonShifts;
    }

    public SpecificShiftToEmployeeAdder getSpecificShiftToEmployeeAdder() {
        return specificShiftToEmployeeAdder;
    }
}
