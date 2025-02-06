package pl.mateuszkruk.Messages;

import org.springframework.stereotype.Component;

@Component
public class EmptyListOfEmployees {

    public static void showMessage(){
        System.out.println("Nie masz żadnych pracowników!");
    }

    public static void showMessage(String message){
        System.out.println("Nie masz żadnych pracowników!" + message);
    }
}
