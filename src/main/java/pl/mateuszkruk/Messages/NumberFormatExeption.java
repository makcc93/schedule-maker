package pl.mateuszkruk.Messages;

import org.springframework.stereotype.Component;

@Component
public class NumberFormatExeption {
    public static void showMessage(){
        System.out.println("Wprowadź poprawną cyfrę!");
    }
}
