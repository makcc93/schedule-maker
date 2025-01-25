package pl.mateuszkruk.Messages;

public class NoEmployeesForWorkSchedule {
    public static void showMessage(){
        System.out.println("UWAGA! Aby stworzyć grafik pracy potrzebujesz pracownikow.");
        EmptyListOfEmployees.showMessage();
    }
}
