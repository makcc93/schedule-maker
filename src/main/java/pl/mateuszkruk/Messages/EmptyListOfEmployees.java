package pl.mateuszkruk.Messages;

public class EmptyListOfEmployees {

    public static void showMessage(){
        System.out.println("Nie masz żadnych pracowników!");
    }

    public static void showMessage(String message){
        System.out.println("Nie masz żadnych pracowników!" + message);
    }
}
