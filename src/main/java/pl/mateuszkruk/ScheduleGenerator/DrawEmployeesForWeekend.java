package pl.mateuszkruk.ScheduleGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Messages.EmptyListOfEmployees;
import pl.mateuszkruk.Schedule.ShiftDraw;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.UniversalMethods.MapSorter;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.LinkedHashMap;
import java.util.Map;
@Component
public class DrawEmployeesForWeekend {
    ShiftDraw shiftDraw;
    private static int returnedSumOfMorningShifts = 0;
    private static int returnedSumOfAfternoonShifts = 0;

    @Autowired
    public DrawEmployeesForWeekend(ShiftDraw shiftDraw) {this.shiftDraw = shiftDraw;}

    public void draw(int highRequirement,
                            int sumOfMorningShifts, int sumOfAfternoonShifts,
                            LinkedHashMap<Employee, Integer> sortedEmployees,
                            Map<Employee, Shifts> employeesForSingleDayMap,
                            SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours) {

        for (int i = 1; i <= highRequirement; i++) {
            if (sumOfAfternoonShifts == highRequirement && sumOfMorningShifts == highRequirement) {
                break;
            }

            sortedEmployees = MapSorter.sortedMapByHoursAscending(sortedEmployees);

            if (sortedEmployees.isEmpty()) {
                EmptyListOfEmployees.showMessage();
                break;
            }
            Employee employeeWithLowestHours = sortedEmployees.keySet().iterator().next();
            Shifts allDayWeekendShifts = shiftDraw.randomAllDayShift();

            MatchShiftWithEmployee.run(employeeWithLowestHours,allDayWeekendShifts,sortedEmployees,
                                        employeesForSingleDayMap,sumOfMonthlyEmployeeHours);
            sumOfMorningShifts++;
            sumOfAfternoonShifts++;
        }
        returnedSumOfMorningShifts = sumOfMorningShifts;
        returnedSumOfAfternoonShifts = sumOfAfternoonShifts;
    }

    public static int getReturnedSumOfMorningShifts() {
        return returnedSumOfMorningShifts;
    }

    public static int getReturnedSumOfAfternoonShifts() {
        return returnedSumOfAfternoonShifts;
    }
}
