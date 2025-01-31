package pl.mateuszkruk.UserInput;
import pl.mateuszkruk.Messages.DefaultInSwitch;
import pl.mateuszkruk.Messages.NumberFormatExeption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner = new Scanner(System.in);

    public String getString(String message){
        System.out.println(message);
        return scanner.nextLine();
    }

    public String getString(){
        return scanner.nextLine();
    }

    public int getInt(String message){
        while(true) {
            try {
                System.out.println(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                NumberFormatExeption.showMessage();
            }
        }
    }


    public int getInt(){
        while(true){
            try{
                return Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e){
                NumberFormatExeption.showMessage();
            }
        }
    }

    public boolean getBoolean(String message) {
        while (true){
            System.out.println(message);
            int choice = getInt();

            if(choice==1){
                return true;
            }
            else if(choice==0){
                return false;
            }
            else{
                DefaultInSwitch.showMessage();
            }
        }
    }

    public boolean getBoolean(){
        while(true){
            int choice = getInt();

            if(choice == 1){
                return true;
            }
            else if(choice == 0){
                return false;
            }
            else{
                System.out.println("Błąd! Wciśnij 1 lub 0!");
            }
        }
    }

    public List<Integer> getListOfIntegers(){
        List<Integer> integers = new ArrayList<>();

        System.out.print("Wpisz liczby oddzielone przecinkami (np. 1,2,3): ");
        String input = scanner.nextLine();

        try{
            String[] numbers = input.split(",");

            for (String number : numbers){
                integers.add(Integer.valueOf(number.trim()));
            }
        }
        catch(NumberFormatException e){
            NumberFormatExeption.showMessage();
            integers.clear();
        }

        return integers;
    }

    public List<Integer> getListOfIntegers(String message){
        List<Integer> integers = new ArrayList<>();

        System.out.print("Wpisz liczby oddzielone przecinkami (np. 1,2,3): " + message);
        String input = scanner.nextLine();

        try{
            String[] numbers = input.split(",");

            for (String number : numbers){
                integers.add(Integer.valueOf(number.trim()));
            }
        }
        catch(NumberFormatException e){
            NumberFormatExeption.showMessage();
            integers.clear();
        }

        return integers;
    }

    public void closeScanner(){
        scanner.close();
    }

}