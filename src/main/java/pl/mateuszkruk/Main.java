package pl.mateuszkruk;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.EmployeeRemover;
import pl.mateuszkruk.Employee.EmployeeAdder;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Employee.SpecialEmployeesSet;
import pl.mateuszkruk.Menu.MainMenu;
import pl.mateuszkruk.Menu.StepsInMenu.ChooseDrawPriority;
import pl.mateuszkruk.Menu.StepsInMenu.SetSpecificShiftToEmployee;
import pl.mateuszkruk.Schedule.ShiftDraw;
import pl.mateuszkruk.ScheduleGenerator.DrawEmployeesForWeekend;
import pl.mateuszkruk.ScheduleGenerator.DrawSpecificShifts;
import pl.mateuszkruk.UserInput.InputHandler;
import pl.mateuszkruk.WorkTime.SpecificShiftToEmployeeAdder;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.VacationAdder;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;
import pl.mateuszkruk.Schedule.FirstDayAndLenghtOfMonth;
import pl.mateuszkruk.ScheduleGenerator.FullMonthScheduleGenerator;
import pl.mateuszkruk.ScheduleGenerator.SingleDayDraw;
import pl.mateuszkruk.Schedule.DaysOfWeek;
import java.util.Random;

@Component
public class Main {
    Random random;
    ShiftDraw shiftDraw;
    InputHandler inputHandler;
    EmployeeListsMatcher employeeListsMatcher;
    SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder;
    SpecialEmployeesSet specialEmployeesSet;
    EmployeeRemover employeeRemover;
    EmployeeAdder employeeAdder;
    SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;
    DrawSpecificShifts drawSpecificShifts;
    PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours;
    FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;
    SetSpecificShiftToEmployee setSpecificShiftToEmployee;
    VacationAdder vacationAdder;
    EmployeeProposalFreeDays employeeProposalFreeDays;
    ChooseDrawPriority chooseDrawPriority;
    SingleDayDraw singleDayDraw;
    FullMonthScheduleGenerator fullMonthScheduleGenerator;
    DrawEmployeesForWeekend drawEmployeesForWeekend;
    MainMenu mainMenu;

    @Autowired
    public Main(Random random, ShiftDraw shiftDraw, EmployeeListsMatcher employeeListsMatcher, SpecialEmployeesSet specialEmployeesSet,
                EmployeeRemover employeeRemover, EmployeeAdder employeeAdder, SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours, DrawSpecificShifts drawSpecificShifts,
                PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours, FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth, SetSpecificShiftToEmployee setSpecificShiftToEmployee,
                VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays, ChooseDrawPriority chooseDrawPriority, SingleDayDraw singleDayDraw,
                FullMonthScheduleGenerator fullMonthScheduleGenerator, DrawEmployeesForWeekend drawEmployeesForWeekend, InputHandler inputHandler,
                MainMenu mainMenu) {
        this.random = random;
        this.shiftDraw = shiftDraw;
        this.employeeListsMatcher = employeeListsMatcher;
        this.specialEmployeesSet = specialEmployeesSet;
        this.employeeRemover = employeeRemover;
        this.employeeAdder = employeeAdder;
        this.sumOfMonthlyEmployeeHours = sumOfMonthlyEmployeeHours;
        this.drawSpecificShifts = drawSpecificShifts;
        this.personalMonthlyStandardWorkingHours = personalMonthlyStandardWorkingHours;
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
        this.setSpecificShiftToEmployee = setSpecificShiftToEmployee;
        this.vacationAdder = vacationAdder;
        this.employeeProposalFreeDays = employeeProposalFreeDays;
        this.chooseDrawPriority = chooseDrawPriority;
        this.singleDayDraw = singleDayDraw;
        this.fullMonthScheduleGenerator = fullMonthScheduleGenerator;
        this.drawEmployeesForWeekend = drawEmployeesForWeekend;
        this.inputHandler = inputHandler;
        this.mainMenu = mainMenu;

        firstDayAndLenghtOfMonth.setMonthDetails(DaysOfWeek.MONDAY, 31);
    }

    @PostConstruct
    public void run() throws Exception {
        mainMenu.run();

        inputHandler.closeScanner();
    }
}