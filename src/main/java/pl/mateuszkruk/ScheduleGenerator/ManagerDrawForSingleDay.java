package pl.mateuszkruk.ScheduleGenerator;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Messages.EmptyListOfEmployees;
import pl.mateuszkruk.Schedule.*;
import pl.mateuszkruk.UniversalMethods.*;
import pl.mateuszkruk.WorkTime.*;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class ManagerDrawForSingleDay {
    private final EmployeeListsMatcher employeeListsMatcher;
    private final SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;
    private final VacationAdder vacationAdder;
    private final PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours;
    private final EmployeeProposalFreeDays employeeProposalFreeDays;
    private final FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;
    private final ShiftRequirements shiftRequirements;
    private final SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder;
    private final int highRequirement = DaysOfWeek.getSaturdayRequirements();




    public ManagerDrawForSingleDay(EmployeeListsMatcher employeeListsMatcher,
                                   SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                   VacationAdder vacationAdder,
                                   PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                                   EmployeeProposalFreeDays employeeProposalFreeDays,
                                   FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth,
                                   ShiftRequirements shiftRequirements,
                                   SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder
                                   ) {
        this.employeeListsMatcher = employeeListsMatcher;
        this.sumOfMonthlyEmployeeHours = sumOfMonthlyEmployeeHours;
        this.vacationAdder = vacationAdder;
        this.personalMonthlyStandardWorkingHours = personalMonthlyStandardWorkingHours;
        this.employeeProposalFreeDays = employeeProposalFreeDays;
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
        this.shiftRequirements = shiftRequirements;
        this.specificShiftToEmployeeAdder = specificShiftToEmployeeAdder;
    }

    public void drawManager(Map<Employee, Shifts> employeesForSingleDayMap, int dayOfMonth) {
        DaysOfWeek thisDay = firstDayAndLenghtOfMonth.getMonthSchedule(dayOfMonth);
        int dayRequirement = shiftRequirements.getSpecificDayRequirements(dayOfMonth);

        List<Employee> filteredEmployees = FilterListOfEmployeesCantWork.managers(dayOfMonth, employeeListsMatcher, vacationAdder,
                employeeProposalFreeDays, sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours);

        LinkedHashMap<Employee, Integer> lowestHoursWorkedManagers = PrepareSortConvertListToMap.run(filteredEmployees,
                sumOfMonthlyEmployeeHours, personalMonthlyStandardWorkingHours);


        boolean managerOpenAndClose = false;
        while (!managerOpenAndClose) {
            if (lowestHoursWorkedManagers.isEmpty()){
                EmptyListOfEmployees.showMessage(" dzień: "+ dayOfMonth);
                break;
            }

            Shifts allDayShift = Shifts.EIGHT_TO_TWENTY;


            if (lowestHoursWorkedManagers.size() == 1) {
                Employee onlyAvailableEmployee = lowestHoursWorkedManagers.keySet().iterator().next();

                MatchShiftWithEmployee.run(onlyAvailableEmployee, allDayShift, lowestHoursWorkedManagers,
                        employeesForSingleDayMap, sumOfMonthlyEmployeeHours);
                managerOpenAndClose = true;
            }
            else {
                if (specificShiftToEmployeeAdder.getSpecificShifts().get(dayOfMonth) != null) {
                    Map<Employee, Shifts> map = specificShiftToEmployeeAdder.getSpecificShifts().get(dayOfMonth);

                    for (Map.Entry<Employee, Shifts> employeesShift : map.entrySet()) {
                        Employee employee = employeesShift.getKey();
                        Shifts shift = employeesShift.getValue();

                        if (lowestHoursWorkedManagers.containsKey(employee)) {
                            MatchShiftWithEmployee.run(employee,shift,lowestHoursWorkedManagers,
                                                        employeesForSingleDayMap,sumOfMonthlyEmployeeHours);

                        }
                    }
                }

                if (WeekendDayChecker.checkDay(thisDay, dayRequirement, highRequirement)) {
                    lowestHoursWorkedManagers = MapSorter.updateSorting(lowestHoursWorkedManagers);

                    if (lowestHoursWorkedManagers.isEmpty()) {
                        EmptyListOfEmployees.showMessage(" dzień: "+ dayOfMonth);
                        break;
                    }

                    Employee randomEmployee = lowestHoursWorkedManagers.keySet().iterator().next();

                    MatchShiftWithEmployee.run(randomEmployee, allDayShift, lowestHoursWorkedManagers,
                                                employeesForSingleDayMap, sumOfMonthlyEmployeeHours);
                }

                lowestHoursWorkedManagers = MapSorter.updateSorting(lowestHoursWorkedManagers);

                if (lowestHoursWorkedManagers.isEmpty()) {
                        EmptyListOfEmployees.showMessage(" dzień: "+ dayOfMonth);
                        break;
                    }

                Shifts randomShift = ShiftDraw.drawRandomShift();
                Employee lowestHoursEmployee = lowestHoursWorkedManagers.keySet().iterator().next();

                MatchShiftWithEmployee.run(lowestHoursEmployee, randomShift, lowestHoursWorkedManagers,
                                            employeesForSingleDayMap, sumOfMonthlyEmployeeHours);

                lowestHoursWorkedManagers = MapSorter.updateSorting(lowestHoursWorkedManagers);

                if (!randomShift.isOpenStore() || !randomShift.isCloseStore()) {
                        if (lowestHoursWorkedManagers.isEmpty()) {
                            EmptyListOfEmployees.showMessage(" dzień: "+ dayOfMonth);
                            break;
                        }

                        Employee secondLowestHoursEmployee = lowestHoursWorkedManagers.keySet().iterator().next();

                        if (!randomShift.isOpenStore() && randomShift.isCloseStore()) {
                            Shifts randomOpenStoreShift = ShiftDraw.randomOpenStoreShift();

                            MatchShiftWithEmployee.run(secondLowestHoursEmployee, randomOpenStoreShift, lowestHoursWorkedManagers,
                                                        employeesForSingleDayMap, sumOfMonthlyEmployeeHours);
                        }

                        if (!randomShift.isCloseStore() && randomShift.isOpenStore()) {
                            Shifts randomCloseStoreShift = ShiftDraw.randomCloseStoreShift();

                            MatchShiftWithEmployee.run(secondLowestHoursEmployee, randomCloseStoreShift, lowestHoursWorkedManagers,
                                                        employeesForSingleDayMap, sumOfMonthlyEmployeeHours);
                        }

                        if (!randomShift.isOpenStore() && !randomShift.isCloseStore()) {
                            MatchShiftWithEmployee.run(secondLowestHoursEmployee, allDayShift, lowestHoursWorkedManagers,
                                                        employeesForSingleDayMap, sumOfMonthlyEmployeeHours);
                        }
                    }
                    managerOpenAndClose = true;
                }
            }
        }
    }