package pl.mateuszkruk.ScheduleGenerator;
import java.util.*;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Messages.EmptyListOfEmployees;
import pl.mateuszkruk.UniversalMethods.*;
import pl.mateuszkruk.WorkTime.*;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Schedule.Shifts;

public class CreditEmployeeDrawForSingleDay {
    PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours;
    private final EmployeeProposalFreeDays employeeProposalFreeDays;
    private final SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder;
    private final SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;
    private final EmployeeListsMatcher employeeListsMatcher;
    private final VacationAdder vacationAdder;
    private final SingleDayDraw singleDayDraw;
    protected int numberOfCreditEmployees;
    protected int morningSumOfEmployees;
    protected int afternoonSumOfEmployees;
    ArrayList<Employee> chosenCreditEmployee;

    public CreditEmployeeDrawForSingleDay(EmployeeListsMatcher employeeListsMatcher,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                          VacationAdder vacationAdder,
                                          EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder, SingleDayDraw singleDayDraw){
        this.employeeListsMatcher = employeeListsMatcher;
        this.sumOfMonthlyEmployeeHours = sumOfMonthlyEmployeeHours;
        this.personalMonthlyStandardWorkingHours = personalMonthlyStandardWorkingHours;
        this.vacationAdder = vacationAdder;
        this.employeeProposalFreeDays=employeeProposalFreeDays;
        this.specificShiftToEmployeeAdder = specificShiftToEmployeeAdder;
        this.singleDayDraw = singleDayDraw;
    }

    public void drawCreditEmployee(Map<Employee, Shifts> employeesForSingleDayMap, int dayOfMonth) {

        numberOfCreditEmployees = 0;
        morningSumOfEmployees = 0;
        afternoonSumOfEmployees = 0;
        int lowestCreditEmployeeNeeded = 1;

        chosenCreditEmployee = new ArrayList<>();
        List<Employee> creditEmployees = FilterListOfEmployeesCantWork.creditEmployees(dayOfMonth,employeeListsMatcher,
                vacationAdder,employeeProposalFreeDays, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours,singleDayDraw.getFinalSchedule());
        RemoveEmployeeSpecificShift.remove(creditEmployees,specificShiftToEmployeeAdder,dayOfMonth);

        LinkedHashMap<Employee, Integer> lowestHoursWorkedCreditEmployees = PrepareSortConvertListToMap.run(
                creditEmployees,sumOfMonthlyEmployeeHours,personalMonthlyStandardWorkingHours);


        for (int i = 0; i < lowestCreditEmployeeNeeded; i++) {
            if (lowestHoursWorkedCreditEmployees.isEmpty()){
                EmptyListOfEmployees.showMessage(" dzień: " + dayOfMonth);
                break;
            }

            Employee employee = lowestHoursWorkedCreditEmployees.keySet().iterator().next();
            Shifts allDayShifts = Shifts.EIGHT_TO_TWENTY;

            MatchShiftWithEmployee.run(employee,allDayShifts,lowestHoursWorkedCreditEmployees,
                                        employeesForSingleDayMap,sumOfMonthlyEmployeeHours);

            morningSumOfEmployees++;
            afternoonSumOfEmployees++;
            numberOfCreditEmployees++;
            chosenCreditEmployee.add(employee);
        }
    }

    public int getMorningSumOfEmployees() {
        return morningSumOfEmployees;
    }

    public int getAfternoonSumOfEmployees() {
        return afternoonSumOfEmployees;
    }

    public ArrayList<Employee> getChosenCreditEmployee() {
        return chosenCreditEmployee;
    }
}
