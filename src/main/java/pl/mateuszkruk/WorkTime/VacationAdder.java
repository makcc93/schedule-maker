package pl.mateuszkruk.WorkTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.FirstDayAndLenghtOfMonth;
import pl.mateuszkruk.Schedule.DaysOfWeek;
import pl.mateuszkruk.Schedule.ShiftRequirements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class VacationAdder {
    private final ShiftRequirements shiftRequirements;
    private final PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours;
    private final FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;
    List<Integer> freeDaysOfMonth;
    Map<Integer,DaysOfWeek> saturdays;
    Map<Employee, List<Integer>> employeesVacations = new HashMap<>();

@Autowired
    public VacationAdder(PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                         ShiftRequirements shiftRequirements, FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth){
        this.personalMonthlyStandardWorkingHours = personalMonthlyStandardWorkingHours;
        this.shiftRequirements = shiftRequirements;
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
    }


    public void addVacation(Employee employee, int dayOfStartVacation, int dayOfEndVacation){
        checkingFreeDaysInMonth(dayOfStartVacation,dayOfEndVacation);
        checkingSaturdays(dayOfStartVacation,dayOfEndVacation);
        int numberOfFreeDays = freeDaysOfMonth.size();
        int numberOfSaturdays = saturdays.size();


        int hoursOfSingleVacationDay = 8;
        int differenceOfDaysMultiplyByHours = (dayOfEndVacation-dayOfStartVacation-numberOfFreeDays-numberOfSaturdays)* hoursOfSingleVacationDay;

        List<Integer> vacationDays = new ArrayList<>();

        for (int i=dayOfStartVacation; i<=dayOfEndVacation;i++){
            vacationDays.add(i);
        }

        employeesVacations.put(employee, vacationDays);
        personalMonthlyStandardWorkingHours.updatePersonalMonthlyStandardWorkingHours(employee,differenceOfDaysMultiplyByHours);
        freeDaysOfMonth.clear();
        saturdays.clear();
    }

    private void checkingFreeDaysInMonth(int dayOfStartVacation, int dayOfEndVacation){
        freeDaysOfMonth = new ArrayList<>();
        for (int i =dayOfStartVacation;i<=dayOfEndVacation;i++){
            if (firstDayAndLenghtOfMonth.getShiftRequirements().getSpecificDayRequirements(i) == 0) {
                freeDaysOfMonth.add(i);
            }
        }
    }

    private void checkingSaturdays (int dayOfStartVacation, int dayOfEndVacation){
        saturdays = new HashMap<>();
        for (int i=dayOfStartVacation;i<=dayOfEndVacation;i++){
            DaysOfWeek day = firstDayAndLenghtOfMonth.getMonthSchedule(i);
                if (day.getNumberOfWeekDay()==6){
                    saturdays.put(i, day);
                }
            }
        }


    public Map<Employee, List<Integer>> getEmployeesVacations() {
        return employeesVacations;
    }


}
