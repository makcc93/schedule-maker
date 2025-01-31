package pl.mateuszkruk.ScheduleGenerator;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Messages.EmptyListOfEmployees;
import pl.mateuszkruk.UniversalMethods.*;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SpecificShiftToEmployeeAdder;
import pl.mateuszkruk.WorkTime.VacationAdder;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Schedule.*;

import java.util.*;

public class SingleDayDraw {
    private final SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;
    private final CreditEmployeeDrawForSingleDay creditEmployeeDrawForSingleDay;
    private final ManagerDrawForSingleDay managerDrawForSingleDay;
    private final VacationAdder vacationAdder;
    private final EmployeeListsMatcher employeeListsMatcher;
    private final ShiftRequirements shiftRequirements;
    private final int highRequirement = DaysOfWeek.getSaturdayRequirements();
    private final PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours;
    private final DrawSpecificShifts drawSpecificShifts;
    private final SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder;
    private final EmployeeProposalFreeDays employeeProposalFreeDays;
    private final FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;


    Map<Integer, Map<Employee, Shifts>> finalSchedule = new HashMap<>();
    Map<Employee, Shifts> employeesForSingleDay = new HashMap<>();
    Map<Integer, SumOfShifts> dayAndNumberOfEmployees = new HashMap<>();

    public SingleDayDraw(FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth,
                         EmployeeListsMatcher employeeListsMatcher, ShiftRequirements shiftRequirements,
                         SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                         VacationAdder vacationAdder, PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                         EmployeeProposalFreeDays employeeProposalFreeDays,
                         DrawSpecificShifts drawSpecificShifts,
                         SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder) {
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
        this.employeeListsMatcher = employeeListsMatcher;
        this.shiftRequirements = shiftRequirements;
        this.personalMonthlyStandardWorkingHours = personalMonthlyStandardWorkingHours;
        this.drawSpecificShifts = drawSpecificShifts;
        this.specificShiftToEmployeeAdder = specificShiftToEmployeeAdder;
        this.managerDrawForSingleDay = new ManagerDrawForSingleDay(employeeListsMatcher, sumOfMonthlyEmployeeHours, vacationAdder, personalMonthlyStandardWorkingHours, employeeProposalFreeDays,firstDayAndLenghtOfMonth,shiftRequirements,specificShiftToEmployeeAdder,this);
        this.creditEmployeeDrawForSingleDay = new CreditEmployeeDrawForSingleDay(employeeListsMatcher, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours, vacationAdder, employeeProposalFreeDays, specificShiftToEmployeeAdder,this);
        this.sumOfMonthlyEmployeeHours = sumOfMonthlyEmployeeHours;
        this.vacationAdder = vacationAdder;
        this.employeeProposalFreeDays = employeeProposalFreeDays;
    }

    public void drawEmployeesSingleDay(int dayOfMonth) {
        DaysOfWeek thisDay = firstDayAndLenghtOfMonth.getMonthSchedule(dayOfMonth);
        int dayRequirement = shiftRequirements.getSpecificDayRequirements(dayOfMonth);
        int sumOfMorningShifts = 0;
        int sumOfAfternoonShifts = 0;

        if (dayRequirement > 0) {
            List<Employee> filteredEmployees = FilterListOfEmployeesCantWork.notManagers(dayOfMonth,employeeListsMatcher,
                    vacationAdder,employeeProposalFreeDays,sumOfMonthlyEmployeeHours,
                    personalMonthlyStandardWorkingHours,getFinalSchedule());

            LinkedHashMap<Employee,Integer> lowestHoursWorkedEmployees = PrepareSortConvertListToMap.run(filteredEmployees,
                    sumOfMonthlyEmployeeHours,personalMonthlyStandardWorkingHours);

            drawManagersAndCreditEmployees(employeesForSingleDay,lowestHoursWorkedEmployees,dayOfMonth);
            drawSpecificShifts.draw(employeesForSingleDay,lowestHoursWorkedEmployees,sumOfMonthlyEmployeeHours,dayOfMonth);


            sumOfMorningShifts = creditEmployeeDrawForSingleDay.getMorningSumOfEmployees() +
                                    drawSpecificShifts.getSumOfMorningShifts();
            sumOfAfternoonShifts = creditEmployeeDrawForSingleDay.getAfternoonSumOfEmployees() +
                                    drawSpecificShifts.getSumOfAfternoonShifts();

            if (WeekendDayChecker.checkDay(thisDay,dayRequirement,highRequirement)) {
                DrawEmployeesForWeekend.draw(highRequirement, sumOfMorningShifts, sumOfAfternoonShifts,
                        lowestHoursWorkedEmployees,
                        employeesForSingleDay,
                        sumOfMonthlyEmployeeHours);

                sumOfMorningShifts = DrawEmployeesForWeekend.getReturnedSumOfMorningShifts();
                sumOfAfternoonShifts = DrawEmployeesForWeekend.getReturnedSumOfAfternoonShifts();
            }
            else {
                while (dayRequirement >= sumOfMorningShifts && dayRequirement >= sumOfAfternoonShifts) {
                    if (dayRequirement == sumOfMorningShifts && dayRequirement == sumOfAfternoonShifts) {
                        break;
                    }

                    lowestHoursWorkedEmployees = MapSorter.sortedMapByHoursAscending(lowestHoursWorkedEmployees);
                        if (lowestHoursWorkedEmployees.isEmpty()) {
                            EmptyListOfEmployees.showMessage("Dzień: " + dayOfMonth);
                            break;
                        }

                    Employee employeeWithLowestHours = lowestHoursWorkedEmployees.keySet().iterator().next();
                    Shifts randomShift = ShiftDraw.drawRandomShift();

                    MatchShiftWithEmployee.run(employeeWithLowestHours,randomShift,
                            lowestHoursWorkedEmployees,employeesForSingleDay,sumOfMonthlyEmployeeHours);

                            if (randomShift.isMorningShift()){
                                sumOfMorningShifts++;
                            }
                            if (randomShift.isAfternoonShift()){
                                sumOfAfternoonShifts++;
                            }

                    if (dayRequirement < sumOfMorningShifts || dayRequirement < sumOfAfternoonShifts) {

                       removeShiftWithEmployee(employeeWithLowestHours,randomShift, lowestHoursWorkedEmployees);

                            if (randomShift.isMorningShift()){
                                sumOfMorningShifts--;
                            }
                            if (randomShift.isAfternoonShift()){
                                sumOfAfternoonShifts--;
                            }
                    }

                    if (dayRequirement == sumOfMorningShifts && dayRequirement > sumOfAfternoonShifts) {

                        lowestHoursWorkedEmployees = MapSorter.sortedMapByHoursAscending(lowestHoursWorkedEmployees);
                            if (lowestHoursWorkedEmployees.isEmpty()) {
                                EmptyListOfEmployees.showMessage("Dzień: " + dayOfMonth);
                                break;
                            }

                        Shifts afternoonShift = Shifts.FOURTEEN_TO_TWENTY;
                        Employee secondRandomEmployee = lowestHoursWorkedEmployees.keySet().iterator().next();

                        MatchShiftWithEmployee.run(secondRandomEmployee,afternoonShift,
                                                    lowestHoursWorkedEmployees,employeesForSingleDay,sumOfMonthlyEmployeeHours);
                        sumOfAfternoonShifts++;

                    }
                    else if (dayRequirement > sumOfMorningShifts && dayRequirement == sumOfAfternoonShifts) {

                        lowestHoursWorkedEmployees = MapSorter.sortedMapByHoursAscending(lowestHoursWorkedEmployees);
                            if (lowestHoursWorkedEmployees.isEmpty()) {
                                EmptyListOfEmployees.showMessage("Dzień: " + dayOfMonth);
                                break;
                            }

                        Shifts morningShift = Shifts.EIGHT_TO_FOURTEEN;
                        Employee thirdLowestHoursEmployee = lowestHoursWorkedEmployees.keySet().iterator().next();

                        MatchShiftWithEmployee.run(thirdLowestHoursEmployee,morningShift,
                                                    lowestHoursWorkedEmployees,employeesForSingleDay,sumOfMonthlyEmployeeHours);
                        sumOfMorningShifts++;

                    }
                }
            }
            finalSchedule.put(dayOfMonth, new HashMap<>(employeesForSingleDay));
            dayAndNumberOfEmployees.put(dayOfMonth, new SumOfShifts(sumOfMorningShifts, sumOfAfternoonShifts));
            employeesForSingleDay.clear();
        }
    }

    private void drawManagersAndCreditEmployees(Map<Employee, Shifts> employeesForSingleDay,
                                                LinkedHashMap<Employee,Integer> preparedAndSortedEmployees,
                                                int dayOfMonth){
        managerDrawForSingleDay.drawManager(employeesForSingleDay,dayOfMonth);
        creditEmployeeDrawForSingleDay.drawCreditEmployee(employeesForSingleDay,dayOfMonth);

        for (Employee employee : creditEmployeeDrawForSingleDay.getChosenCreditEmployee()) {
            preparedAndSortedEmployees.remove(employee);
        }
    }

    private void removeShiftWithEmployee(Employee employeeWithLowestHours, Shifts shift,
                                        LinkedHashMap<Employee, Integer> lowestHoursWorkedEmployee){
        employeesForSingleDay.remove(employeeWithLowestHours, shift);
        sumOfMonthlyEmployeeHours.removeHours(employeeWithLowestHours, shift.getNumberOfHours());
        lowestHoursWorkedEmployee.put(employeeWithLowestHours,shift.getNumberOfHours());
    }

    public Map<Integer, Map<Employee, Shifts>> getFinalSchedule() {

        return finalSchedule;
    }

    public Map<Integer, SumOfShifts> getDayAndNumberOfEmployees() {

        return dayAndNumberOfEmployees;
    }

}