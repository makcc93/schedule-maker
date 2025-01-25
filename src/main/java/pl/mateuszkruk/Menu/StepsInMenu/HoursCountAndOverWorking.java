package pl.mateuszkruk.Menu.StepsInMenu;

import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.UserInput.InputHandler;
import pl.mateuszkruk.WorkTime.OverWorkingHours;
import pl.mateuszkruk.WorkTime.PersonalMonthlyStandardWorkingHours;
import pl.mateuszkruk.WorkTime.StandardMonthlyWorkingHours;

public class HoursCountAndOverWorking {
private final InputHandler inputHandler;
private final PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours;

    public HoursCountAndOverWorking(InputHandler inputHandler,
                                    PersonalMonthlyStandardWorkingHours personalMonthlyStandardWorkingHours){
        this.inputHandler=inputHandler;
        this.personalMonthlyStandardWorkingHours = personalMonthlyStandardWorkingHours;
    }

    public void run() {
        int step = 2;
        DrawLine.draw();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println(".:: Krok " + step + " tworzenia grafiku - czas pracy i nadgodziny ::." +
                    System.lineSeparator());

                int firstUserChoice = inputHandler.getInt("         Podaj standardową ilość godzin do przepracowania w tym miesiącu przez jednego pracownika:");

                System.out.println("Poprawnie ustalony został wymiar czasu pracy na " + firstUserChoice + " godzin." +
                        System.lineSeparator());

                int secondUserChoice = inputHandler.getInt("        Ustal wymiar możliwych nadgodzin dla jednego pracownika (domyślnie 0):");

                System.out.println(System.lineSeparator() +
                        "Standardowy wymiar czasu pracy: " + firstUserChoice);

                System.out.println("Wymiar możliwych nadgodzin pracownika: " + secondUserChoice);

                isRunning = !inputHandler.getBoolean("Jeśli potwiedzasz wciśnij 1, jeśli chcesz przejść Krok " +
                        step + " raz jeszcze wciśnij 0");

                if(!isRunning){
                    StandardMonthlyWorkingHours.getInstance().setStandardMonthlyWorkingHours(firstUserChoice);
                    personalMonthlyStandardWorkingHours.setPersonalMonthlyStandardWorkingHours(firstUserChoice);
                    OverWorkingHours.OVER_WORKING_HOURS.setNumberOfOverWorkingHoursPerEmployee(secondUserChoice);
                }
        }

    }
}
