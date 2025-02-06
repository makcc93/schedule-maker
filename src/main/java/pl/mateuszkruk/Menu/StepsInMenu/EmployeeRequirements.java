package pl.mateuszkruk.Menu.StepsInMenu;

import org.springframework.stereotype.Component;
import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.Schedule.FirstDayAndLenghtOfMonth;
import pl.mateuszkruk.Schedule.DaysOfWeek;
import pl.mateuszkruk.UserInput.InputHandler;

@Component
public class EmployeeRequirements {
    private final InputHandler inputHandler;
    private final FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;

    public EmployeeRequirements(InputHandler inputHandler, FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth) {
        this.inputHandler = inputHandler;
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
    }

    public void run() {
        int step = 3;
        DrawLine.draw();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println(".:: Krok " + step + " tworzenia grafiku - ustalenie zapotrzebowania pracowników ::." +
                    System.lineSeparator());
            System.out.println("        Tak wygląda zapotrzebowanie standardowe na pracowników dla dni tygodnia:");
            System.out.println(DaysOfWeek.MONDAY.getPolishName() + ": " + DaysOfWeek.MONDAY.getDefaultDayRequirements());
            System.out.println(DaysOfWeek.TUESDAY.getPolishName() + ": " + DaysOfWeek.TUESDAY.getDefaultDayRequirements());
            System.out.println(DaysOfWeek.WEDNESDAY.getPolishName() + ": " + DaysOfWeek.WEDNESDAY.getDefaultDayRequirements());
            System.out.println(DaysOfWeek.THURSDAY.getPolishName() + ": " + DaysOfWeek.THURSDAY.getDefaultDayRequirements());
            System.out.println(DaysOfWeek.FRIDAY.getPolishName() + ": " + DaysOfWeek.FRIDAY.getDefaultDayRequirements());
            System.out.println(DaysOfWeek.SATURDAY.getPolishName() + ": " + DaysOfWeek.SATURDAY.getDefaultDayRequirements());
            System.out.println(DaysOfWeek.SUNDAY.getPolishName() + ": " + DaysOfWeek.SUNDAY.getDefaultDayRequirements());
            System.out.println();

            boolean choiceInsideLoop = inputHandler.getBoolean(
                    "Jeśli nie chcesz zmieniać wciśnij 1, jeśli chcesz zmodyfikować wciśnij 0.");

            while (!choiceInsideLoop) {

                int mondayChoice = inputHandler.getInt("Podaj wymaganą ilość pracowników dla poniedziałków:");

                int tuesdayChoice = inputHandler.getInt(("Podaj wymaganą ilość pracowników dla wtorków:"));

                int wednesdayChoice = inputHandler.getInt("Podaj wymaganą ilość pracowników dla środy:");

                int thursdayChoice = inputHandler.getInt("Podaj wymaganą ilość pracowników dla czwartków:");

                int fridayChoice = inputHandler.getInt("Podaj wymaganą ilość pracowników dla piątków:");

                int saturdayChoice = inputHandler.getInt("Podaj wymaganą ilość pracowników dla sobót:");

                int sundayChoice = inputHandler.getInt("Podaj wymaganą ilość pracowników dla niedziel:");

                System.out.println(System.lineSeparator() +
                        "Tak wygląda zapotrzebowanie standardowe dla dni tygodnia po twoich zmianach:");
                System.out.println(DaysOfWeek.MONDAY.getPolishName() + ": " + mondayChoice + " pracowników.");
                System.out.println(DaysOfWeek.TUESDAY.getPolishName() + ": " + tuesdayChoice + " pracowników.");
                System.out.println(DaysOfWeek.WEDNESDAY.getPolishName() + ": " + wednesdayChoice + " pracowników.");
                System.out.println(DaysOfWeek.THURSDAY.getPolishName() + ": " + thursdayChoice + " pracowników.");
                System.out.println(DaysOfWeek.FRIDAY.getPolishName() + ": " + fridayChoice + " pracowników.");
                System.out.println(DaysOfWeek.SATURDAY.getPolishName() + ": " + saturdayChoice + " pracowników.");
                System.out.println(DaysOfWeek.SUNDAY.getPolishName() + ": " + sundayChoice + " pracowników.");

                choiceInsideLoop = inputHandler.getBoolean("Jeśli się zgadza wciśnij 1, jeśli chcesz zacząć od początku wciśnij 0.");

                if (choiceInsideLoop) {
                    DaysOfWeek.MONDAY.setDefaultDayRequirements(mondayChoice);
                    DaysOfWeek.TUESDAY.setDefaultDayRequirements(tuesdayChoice);
                    DaysOfWeek.WEDNESDAY.setDefaultDayRequirements(wednesdayChoice);
                    DaysOfWeek.THURSDAY.setDefaultDayRequirements(thursdayChoice);
                    DaysOfWeek.FRIDAY.setDefaultDayRequirements(fridayChoice);
                    DaysOfWeek.SATURDAY.setDefaultDayRequirements(saturdayChoice);
                    DaysOfWeek.SUNDAY.setDefaultDayRequirements(sundayChoice);
                    firstDayAndLenghtOfMonth.setSpecificDayRequirementsLikeDefault();
                }
            }

            boolean customSpecificDayRequirement = inputHandler.getBoolean(
                    "       Jeśli potrzebujesz ustalić nietypową liczbę wymaganych pracowników w jakimś dniu wciśnij 1, jeśli nie wciśnij 0.");
            while(customSpecificDayRequirement){
                int day = inputHandler.getInt("Podaj dzień miesiaca dla nietypowego zapotrzebowania:");
                int requirement = inputHandler.getInt("Wpisz zapotrzebowanie pracowników dla tego dnia:");

                firstDayAndLenghtOfMonth.getShiftRequirements().setSpecificDayRequirements(day, requirement);
                System.out.println("Poprawnie dodano zapotrzebowanie dla dnia " + day + " na " + requirement + " pracowników.");

                customSpecificDayRequirement = inputHandler.getBoolean(
                        "Jeśli chcesz dodać kolejne nietypowe zapotrzebowanie wciśnij 1, jeśli nie wciśnij 0.");
            }
        isRunning=false;
        }

    }
}
