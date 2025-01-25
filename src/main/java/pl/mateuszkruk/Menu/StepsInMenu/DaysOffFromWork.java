package pl.mateuszkruk.Menu.StepsInMenu;

import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.UserInput.InputHandler;
import pl.mateuszkruk.Schedule.FirstDayAndLenghtOfMonth;

import java.util.List;
import java.util.StringJoiner;

public class DaysOffFromWork {
    private final InputHandler inputHandler;
    private final FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;

    public DaysOffFromWork(FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth,
                           InputHandler inputHandler){

        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
        this.inputHandler = inputHandler;
    }

    public void run(){
        int step = 4;
        boolean isRunning = true;

        while(isRunning) {
            DrawLine.draw();
            System.out.println(".:: KROK " + step + " - ustawianie dni wolnych, świąt ::." +
                    System.lineSeparator());

            boolean customFreeDay = inputHandler.getBoolean(
                    "       Jeśli chcesz wpisać dni wolne od pracy w tym miesiącu (np. jakieś święto) wciśnij 1, jeśli nie wciśnij 0.");

            while (customFreeDay) {
                List<Integer> days = inputHandler.getListOfIntegers();
                StringJoiner stringJoiner = new StringJoiner(", ");

                for (int day : days){
                    stringJoiner.add(String.valueOf(day));
                }

                System.out.print("Wpisane dni wolne od pracy to: " + stringJoiner + "." +  System.lineSeparator());

                customFreeDay = inputHandler.getBoolean("Jeśli potwiedzasz wciśnij 1, jeśli chcesz wybierać ponownie wciśnij 0.");

                if (!customFreeDay) {
                    for (int day : days) {
                        firstDayAndLenghtOfMonth.getShiftRequirements().setSpecificDayRequirements(day, 0);
                    }
                 }
            }
            isRunning = false;
            }
        }
    }
