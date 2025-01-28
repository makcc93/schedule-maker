package pl.mateuszkruk;

import pl.mateuszkruk.Employee.EmployeeRemover;
import pl.mateuszkruk.Employee.EmployeeAdder;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Employee.SpecialEmployeesSet;
import pl.mateuszkruk.Menu.MainMenu;
import pl.mateuszkruk.Menu.StepsInMenu.ChooseDrawPriority;
import pl.mateuszkruk.Menu.StepsInMenu.SetSpecificShiftToEmployee;
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


public class Main {
    public static void main(String[] args) throws Exception {


        InputHandler inputHandler = new InputHandler();
        EmployeeListsMatcher employeeListsMatcher = new EmployeeListsMatcher();
        SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder = new SpecificShiftToEmployeeAdder();
        SpecialEmployeesSet specialEmployeesSet = new SpecialEmployeesSet();
        EmployeeRemover employeeRemover = new EmployeeRemover();
        EmployeeAdder employeeAdder = new EmployeeAdder();
        SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours = new SumOfMonthlyEmployeeHours();
        DrawSpecificShifts drawSpecificShifts = new DrawSpecificShifts(specificShiftToEmployeeAdder,sumOfMonthlyEmployeeHours,employeeListsMatcher);
        PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours = new PersonalMonthlyStandardWorkingHours(employeeListsMatcher);
        FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth = new FirstDayAndLenghtOfMonth(DaysOfWeek.SATURDAY, 31);
        SetSpecificShiftToEmployee setSpecificShiftToEmployee = new SetSpecificShiftToEmployee(inputHandler, specificShiftToEmployeeAdder, employeeListsMatcher,firstDayAndLenghtOfMonth);
        VacationAdder vacationAdder = new VacationAdder(personalMonthlyStandardWorkingHours, firstDayAndLenghtOfMonth.getShiftRequirements(), firstDayAndLenghtOfMonth);
        EmployeeProposalFreeDays employeeProposalFreeDays = new EmployeeProposalFreeDays();
        ChooseDrawPriority chooseDrawPriority = new ChooseDrawPriority(inputHandler);
        SingleDayDraw singleDayDraw = new SingleDayDraw(firstDayAndLenghtOfMonth, employeeListsMatcher, firstDayAndLenghtOfMonth.getShiftRequirements(),sumOfMonthlyEmployeeHours, vacationAdder,personalMonthlyStandardWorkingHours,employeeProposalFreeDays,drawSpecificShifts,specificShiftToEmployeeAdder);
        FullMonthScheduleGenerator fullMonthScheduleGenerator = new FullMonthScheduleGenerator(singleDayDraw, firstDayAndLenghtOfMonth);

        MainMenu mainMenu = new MainMenu(employeeListsMatcher,employeeRemover,employeeAdder, firstDayAndLenghtOfMonth,
                specialEmployeesSet,inputHandler,personalMonthlyStandardWorkingHours, vacationAdder,employeeProposalFreeDays,
                sumOfMonthlyEmployeeHours,
                singleDayDraw,
                chooseDrawPriority,
                fullMonthScheduleGenerator,
                setSpecificShiftToEmployee);

        mainMenu.run();

        inputHandler.closeScanner();
    }
}