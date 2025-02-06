package pl.mateuszkruk.Messages;

import org.springframework.stereotype.Component;

@Component
public class DefaultInSwitch {
    public static void showMessage(){
        System.out.println("Wciśnij poprawny klawisz!");
    }

    public static void showMessage(String message){
        System.out.println("Wciśnij poprawny klawisz!" + message);
    }
}
