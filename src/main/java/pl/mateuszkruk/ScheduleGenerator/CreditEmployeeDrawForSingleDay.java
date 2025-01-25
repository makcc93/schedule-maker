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
    protected int numberOfCreditEmployees;
    protected int morningSumOfEmployyes;
    protected int afternoonSumOfEmployyes;
    ArrayList<Employee> chosenCreditEmployee;

    public CreditEmployeeDrawForSingleDay(EmployeeListsMatcher employeeListsMatcher,
                                          SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                          PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                          VacationAdder vacationAdder,
                                          EmployeeProposalFreeDays employeeProposalFreeDays,
                                          SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder){
        this.employeeListsMatcher = employeeListsMatcher;
        this.sumOfMonthlyEmployeeHours = sumOfMonthlyEmployeeHours;
        this.personalMonthlyStandardWorkingHours = personalMonthlyStandardWorkingHours;
        this.vacationAdder = vacationAdder;
        this.employeeProposalFreeDays=employeeProposalFreeDays;
        this.specificShiftToEmployeeAdder = specificShiftToEmployeeAdder;
    }

    public void drawCreditEmployee(Map<Employee, Shifts> employeesForSingleDayMap, int dayOfMonth) {

        numberOfCreditEmployees = 0;
        morningSumOfEmployyes = 0;
        afternoonSumOfEmployyes = 0;
        int lowestCreditEmployeeNeeded = 1;

        chosenCreditEmployee = new ArrayList<>();
        List<Employee> creditEmployees = FilterListOfEmployeesCantWork.creditEmployees(dayOfMonth,employeeListsMatcher,
                vacationAdder,employeeProposalFreeDays, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours);
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

            morningSumOfEmployyes++;
            afternoonSumOfEmployyes++;
            numberOfCreditEmployees++;
            chosenCreditEmployee.add(employee);
        }
    }

    public int getMorningSumOfEmployyes() {
        return morningSumOfEmployyes;
    }

    public int getAfternoonSumOfEmployyes() {
        return afternoonSumOfEmployyes;
    }

    public ArrayList<Employee> getChosenCreditEmployee() {
        return chosenCreditEmployee;
    }
}
