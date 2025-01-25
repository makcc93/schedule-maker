package pl.mateuszkruk.Menu;

import pl.mateuszkruk.Employee.*;
import pl.mateuszkruk.Menu.StepsInMenu.*;
import pl.mateuszkruk.Messages.*;
import pl.mateuszkruk.Schedule.*;
import pl.mateuszkruk.ScheduleGenerator.*;
import pl.mateuszkruk.UserInput.InputHandler;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;
import pl.mateuszkruk.WorkTime.VacationAdder;

import java.util.Comparator;



public class MainMenu {
    private final EmployeeListsMatcher employeeListsMatcher;
    private final EmployeeAdder employeeAdder;
    private final EmployeeRemover employeeRemover;
    private final ChooseDrawPriority chooseDrawPriority;
    private final SetSpecificShiftToEmployee setSpecificShiftToEmployee;
    private final SpecialEmployeesSet specialEmployeesSet;
    private final SetFirstDayAndLengthOfMonth setFirstDayAndLengthOfMonth;
    private final HoursCountAndOverWorking hoursCountAndOverWorking;
    private final EmployeeRequirements employeeRequirements;
    private final DaysOffFromWork daysOffFromWork;
    private final VacationAndProposalFreeDays vacationAndProposalFreeDays;
    private final ScheduleGeneratorAndFileExporter scheduleGeneratorAndFileExporter;
    private final InputHandler inputHandler;
    private final String password = "programujodpodstaw.pl";

    public MainMenu(EmployeeListsMatcher employeeListsMatcher,
                    EmployeeRemover employeeRemover,
                    EmployeeAdder employeeAdder,
                    FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth,
                    SpecialEmployeesSet specialEmployeesSet,
                    InputHandler inputHandler,
                    PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours,
                    VacationAdder vacationAdder,
                    EmployeeProposalFreeDays employeeProposalFreeDays,
                    SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                    SingleDayDraw singleDayDraw,
                    ChooseDrawPriority chooseDrawPriority,
                    FullMonthScheduleGenerator fullMonthScheduleGenerator,
                    SetSpecificShiftToEmployee setSpecificShiftToEmployee){

        this.inputHandler=inputHandler;
        this.employeeListsMatcher = employeeListsMatcher;
        this.employeeRemover = employeeRemover;
        this.employeeAdder = employeeAdder;
        this.specialEmployeesSet = specialEmployeesSet;
        this.chooseDrawPriority = chooseDrawPriority;
        this.setSpecificShiftToEmployee = setSpecificShiftToEmployee;
        this.employeeRequirements = new EmployeeRequirements(inputHandler, firstDayAndLenghtOfMonth);
        this.hoursCountAndOverWorking = new HoursCountAndOverWorking(inputHandler,personalMonthlyStandardWorkingHours);
        this.setFirstDayAndLengthOfMonth =new SetFirstDayAndLengthOfMonth(employeeListsMatcher, firstDayAndLenghtOfMonth,inputHandler);
        this.daysOffFromWork = new DaysOffFromWork(firstDayAndLenghtOfMonth,inputHandler);
        this.vacationAndProposalFreeDays = new VacationAndProposalFreeDays(inputHandler, employeeListsMatcher, vacationAdder,employeeProposalFreeDays);
        this.scheduleGeneratorAndFileExporter = new ScheduleGeneratorAndFileExporter(inputHandler, employeeListsMatcher,sumOfMonthlyEmployeeHours, singleDayDraw,fullMonthScheduleGenerator);
    }

    public void run() throws Exception {
        DrawLine.draw();

        boolean programRunning = true;
        while(programRunning) {
            System.out.println("""
                        .:: PROGRAM DO TWORZENIA GRAFIKU PRACY - Schedule Maker ::.
                    Dzięki temu narzędziu możesz przygotować grafik dla pracowników w godzinach 
                    pracy obiektu od 08:00 do 20:00 uwzględniając zmienne zapotrzebowanie na ich 
                    ilość w danych dniach tygodnia i miesiąca, a uwzględniając również potrzeby samych 
                    pracowników pod kątem dni wolnych, preferencji zmian w konkretnych dniach.
                    
                    """);
            System.out.println("[1] Lista pracowników");
            System.out.println("[2] Dodaj pracownika");
            System.out.println("[3] Usuń pracownika");
            System.out.println("[4] Stwórz grafik");
            System.out.println("[5] Użyj specjalnego kodu importu pracowników");
            System.out.println("[6] Zakończ działanie programu");

                int userChoice = inputHandler.getInt();
                switch (userChoice) {
                    case 1 -> {
                        boolean firstMenuOptionRunning = true;

                            while(firstMenuOptionRunning){
                            System.out.println("LISTA PRACOWNIKÓW");
                            System.out.println("[1] Lista wszytskich pracowników");
                            System.out.println("[2] Lista pracowników ratalnych");
                            System.out.println("[3] Lista kierowników");
                            System.out.println("[4] Wyjdź");

                            int choiceInFirst = inputHandler.getInt();
                                if (employeeListsMatcher.getAllEmployees().isEmpty()){
                                    EmptyListOfEmployees.showMessage();
                                    firstMenuOptionRunning=false;
                                }

                            switch(choiceInFirst){
                                    case 1 -> {
                                        System.out.println(".:: LISTA WSZYSTKICH PRACOWNIKÓW ::.");
                                        employeeListsMatcher.getAllEmployees()
                                                .stream()
                                                .sorted(Comparator.comparing(Employee::getFullName))
                                                .forEach(employee -> System.out.println(employee.getFullName() + " SAP: "
                                                        + employee.getSapNumber()));
                                    }
                                    case 2 -> {
                                        System.out.println(".:: LISTA PRACOWNIKÓW RATALNYCH ::.");
                                        employeeListsMatcher.getCreditEmployees()
                                                .stream()
                                                .sorted(Comparator.comparing(Employee::getFullName))
                                                .forEach(employee -> System.out.println(employee.getFullName() + " SAP: "
                                                        + employee.getSapNumber()));
                                    }
                                    case 3 -> {
                                        System.out.println(".:: LISTA KIEROWNIKÓW ::.");
                                        employeeListsMatcher.getManagerEmployees()
                                                .stream()
                                                .sorted(Comparator.comparing(Employee::getFullName))
                                                .forEach(employee -> System.out.println(employee.getFullName() + " SAP: "
                                                        + employee.getSapNumber()));
                                    }
                                    case 4 -> {
                                        firstMenuOptionRunning = false;
                                    }

                                    default -> {
                                        DefaultInSwitch.showMessage();
                                    }
                                }

                        }
                    }

                    case 2 -> {
                        System.out.println("DODAWANIE PRACOWNIKA");
                        employeeAdder.execute(employeeListsMatcher,inputHandler);
                    }
                    case 3 -> {
                        System.out.println("USUWANIE PRACOWNIKA");
                        if (employeeListsMatcher.getAllEmployees().isEmpty()){
                            EmptyListOfEmployees.showMessage();
                        }
                        else{
                        employeeRemover.execute(employeeListsMatcher,inputHandler);
                        }
                    }
                    case 4 -> {
                        System.out.println("TWORZENIE GRAFIKA PRACY");

                            if (employeeListsMatcher.getAllEmployees().isEmpty()){
                                NoEmployeesForWorkSchedule.showMessage();
                                break;
                            }

                            setFirstDayAndLengthOfMonth.run();
                                DrawStepsInMainMenu.firstDone();
                            hoursCountAndOverWorking.run();
                                DrawStepsInMainMenu.secondDone();
                            employeeRequirements.run();
                                DrawStepsInMainMenu.thirdDone();
                            daysOffFromWork.run();
                                DrawStepsInMainMenu.fourthDone();
                            vacationAndProposalFreeDays.run();
                                DrawStepsInMainMenu.fifthDone();
                            chooseDrawPriority.run();
                                DrawStepsInMainMenu.sixthDone();
                            setSpecificShiftToEmployee.run();
                                DrawStepsInMainMenu.seventhDone();
                            scheduleGeneratorAndFileExporter.run();
                    }
                    case 5 -> {
                        String choiceInFifth = inputHandler.getString(".:: WPROWADŹ SPECJALNY KOD IMPORTU PRACOWNIKÓW ::.");

                        if (choiceInFifth.equals(password)){
                            specialEmployeesSet.execute(employeeListsMatcher,inputHandler);
                        }
                    }
                    case 6 -> {
                        programRunning=!inputHandler.getBoolean("Wciśnij 1, aby potwiedzić zamknięcie programu, 0 aby nie zamykać.");

                        if(!programRunning){
                            System.out.println("Dziękujemy za skorzystanie z programu .::Schedule Maker::.");
                        }
                    }
                    default -> {
                        DefaultInSwitch.showMessage();
                    }


                }
        }
    }
}
