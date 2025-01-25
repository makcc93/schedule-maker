package pl.mateuszkruk.Menu.StepsInMenu;

import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.ScheduleGenerator.PriorityInDraw;
import pl.mateuszkruk.UserInput.InputHandler;

public class ChooseDrawPriority {
    private final InputHandler inputHandler;

    public ChooseDrawPriority(InputHandler inputHandler){
        this.inputHandler=inputHandler;
    }

    public void run(){
        int step = 6;
        DrawLine.draw();
        System.out.println(".:: KROK " + step + " ustalenie priorytetu generowania grafiku" +
                System.lineSeparator());

        System.out.println("Ustawienie priorytetu na weekend da pewność wymaganej obsady pracowników w sobotę (i niedzięlę, jeśli jest pracująca).");
        boolean choice;
        choice = inputHandler.getBoolean("      Jeśli chcesz ustalić priorytet układania grafiku dla weekendów wciśnij 1, jeśli nie wciśnij 0.");

        PriorityInDraw.setPriority(choice);
        if (choice){
            System.out.println("Priorytet weekendowy został ustawiony pomyślnie.");
        }
        else{
            System.out.println("Grafik będzie generowany bez priorytetu.");
        }

    }
}
