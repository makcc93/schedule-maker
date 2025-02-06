package pl.mateuszkruk.Menu.StepsInMenu;

import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Messages.DefaultInSwitch;
import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.Schedule.FirstDayAndLenghtOfMonth;
import pl.mateuszkruk.Schedule.DaysOfWeek;
import pl.mateuszkruk.UserInput.InputHandler;

@Component
public class SetFirstDayAndLengthOfMonth {
    private final InputHandler inputHandler;
    EmployeeListsMatcher employeeListsMatcher;
    FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;

    public SetFirstDayAndLengthOfMonth(EmployeeListsMatcher employeeListsMatcher, FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth,
                                       InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.employeeListsMatcher = employeeListsMatcher;
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
    }

    public void run() {
        int step = 1;

        boolean isRunning = true;
        while (isRunning) {
            DrawLine.draw();
            System.out.println(".:: Krok " + step + " tworzenia grafiku - pierwszy dzień miesiąca i jego długość ::." +
                    System.lineSeparator());
            boolean firstStep = true;
while(firstStep) {
    System.out.println("        Wybierz pierwszy dzień miesiąca:");
    System.out.println("[1] " + DaysOfWeek.MONDAY.getPolishName());
    System.out.println("[2] " + DaysOfWeek.TUESDAY.getPolishName());
    System.out.println("[3] " + DaysOfWeek.WEDNESDAY.getPolishName());
    System.out.println("[4] " + DaysOfWeek.THURSDAY.getPolishName());
    System.out.println("[5] " + DaysOfWeek.FRIDAY.getPolishName());
    System.out.println("[6] " + DaysOfWeek.SATURDAY.getPolishName());
    System.out.println("[7] " + DaysOfWeek.SUNDAY.getPolishName());

    int firstUserChoice = inputHandler.getInt();

    switch (firstUserChoice) {
        case 1 -> {
            firstDayAndLenghtOfMonth.setFirstDayOfMonth(DaysOfWeek.MONDAY);
            firstStep = false;
        }
        case 2 -> {
            firstDayAndLenghtOfMonth.setFirstDayOfMonth(DaysOfWeek.TUESDAY);
            firstStep = false;
        }
        case 3 -> {
            firstDayAndLenghtOfMonth.setFirstDayOfMonth(DaysOfWeek.WEDNESDAY);
            firstStep = false;
        }
        case 4 -> {
            firstDayAndLenghtOfMonth.setFirstDayOfMonth(DaysOfWeek.THURSDAY);
            firstStep = false;
        }
        case 5 -> {
            firstDayAndLenghtOfMonth.setFirstDayOfMonth(DaysOfWeek.FRIDAY);
            firstStep = false;
        }
        case 6 -> {
            firstDayAndLenghtOfMonth.setFirstDayOfMonth(DaysOfWeek.SATURDAY);
            firstStep = false;
        }
        case 7 -> {
            firstDayAndLenghtOfMonth.setFirstDayOfMonth(DaysOfWeek.SUNDAY);
            firstStep = false;
        }
        default -> {
            DefaultInSwitch.showMessage("Wybrany został losowy pierwszy dzień tygodnia! Spróbuj ponownie.");
        }
    }
    System.out.println("Jako pierwszy dzień miesiąca wybrałeś: " + firstDayAndLenghtOfMonth.getFirstDayOfMonth().getPolishName() +
            System.lineSeparator());


            boolean choosingLength = true;

            while (choosingLength) {
                int secondUserChoice = inputHandler.getInt("        Wpisz długość miesiaca w dniach (np. 30):");
                if (secondUserChoice > 31 || secondUserChoice < 1) {
                    System.out.println("Błąd! Miesiąc nie może mieć tyle dni.");
                } else {
                    firstDayAndLenghtOfMonth.setLengthOfMonth(secondUserChoice);
                    choosingLength = false;
                }


                System.out.println(System.lineSeparator() +
                        "Pierwszy dzień miesiąca: " + firstDayAndLenghtOfMonth.getFirstDayOfMonth().getPolishName());

                System.out.println("Długość miesiąca: " + firstDayAndLenghtOfMonth.getLengthOfMonth());

                firstDayAndLenghtOfMonth.setMonthDetails(firstDayAndLenghtOfMonth.getFirstDayOfMonth(),firstDayAndLenghtOfMonth.getLengthOfMonth());

                isRunning = !inputHandler.getBoolean("Jeśli potwiedzasz wciśnij 1, jeśli chcesz przejść Krok " +
                        step + " raz jeszcze wciśnij 0.");
            }

        }
        }
    }
}
