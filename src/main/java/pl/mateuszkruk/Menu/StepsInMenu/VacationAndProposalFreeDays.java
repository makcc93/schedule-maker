package pl.mateuszkruk.Menu.StepsInMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.UserInput.InputHandler;
import pl.mateuszkruk.WorkTime.EmployeeProposalFreeDays;
import pl.mateuszkruk.Messages.NotFound;
import pl.mateuszkruk.WorkTime.VacationAdder;
import java.util.*;
@Component
public class VacationAndProposalFreeDays {
    private final InputHandler inputHandler;
    private final EmployeeListsMatcher employeeListsMatcher;
    private final VacationAdder vacationAdder;
    private final EmployeeProposalFreeDays employeeProposalFreeDays;
@Autowired
    public VacationAndProposalFreeDays(InputHandler inputHandler,
                                       EmployeeListsMatcher employeeListsMatcher,
                                       VacationAdder vacationAdder, EmployeeProposalFreeDays employeeProposalFreeDays){
        this.inputHandler = inputHandler;
        this.employeeListsMatcher = employeeListsMatcher;
        this.vacationAdder = vacationAdder;
        this.employeeProposalFreeDays = employeeProposalFreeDays;
    }

    public void run() throws Exception {
        DrawLine.draw();

        boolean isRunning = true;
        while(isRunning) {
            int step = 5;

            System.out.println(".:: KROK " + step + " - urlopy oraz propozycje dni wolnych ::." +
                    System.lineSeparator());

            boolean addVacation = inputHandler.getBoolean("     Jeśli chcesz dodać urlop pracownika wciśnij 1, jeśli nie i pominąć wciśnij 0.");

            while(addVacation) {
            String employeeBySapNumber = inputHandler.getString("Podaj numer SAP pracownika, któremu chcesz dodać urlop:");

            if (employeeListsMatcher.getAllEmployees().contains(employeeListsMatcher.getEmployee(employeeBySapNumber))) {
                System.out.println("Pracownik " + employeeListsMatcher.getEmployee(employeeBySapNumber).getFullName() +
                        " - SAP: " + employeeListsMatcher.getEmployee(employeeBySapNumber).getSapNumber());

                    int firstVacationDay = inputHandler.getInt("Podaj pierwszy dzień od kiedy rozpoczyna się urlop:");
                    int lastVacationDay = inputHandler.getInt("Podaj ostatni dzień kiedy kończy się urlop:");

                    System.out.println("Pracownik " + employeeListsMatcher.getEmployee(employeeBySapNumber).getFullName() +
                            " ma urlop od " + firstVacationDay + " do " + lastVacationDay + ".");

                    addVacation = !inputHandler.getBoolean("Jeśli potwierdzasz wciśnij 1, jeśli chcesz anulować i spróbować ponownie wciśnij 0.");

                    if (!addVacation) {
                        this.vacationAdder.addVacation(employeeListsMatcher.getEmployee(employeeBySapNumber), firstVacationDay, lastVacationDay);
                    }

            }
            else {
                NotFound.showMessage();
                addVacation=false;
            }

            addVacation = inputHandler.getBoolean("     Jeśli chcesz dodać urlop dla innego pracownika wciśnij 1, jeśli nie wciśnij 0.");
        }

        boolean addProposalFreeDays = inputHandler.getBoolean("Aby dodać propozycję dni wolnych pracowników wciśnij 1, jeśli nie wciśnij 0.");
        while (addProposalFreeDays){
            String employee = inputHandler.getString("Podaj numer SAP pracownika, aby dodać propozycje dni wolnych:");

            boolean adding = true;
            while (adding) {
                if (employeeListsMatcher.getAllEmployees().contains(employeeListsMatcher.getEmployee(employee))) {
                    System.out.println("Pracownik "+ employeeListsMatcher.getEmployee(employee).getFullName() + ".");

                    List<Integer> freeDays = inputHandler.getListOfIntegers();
                    StringJoiner stringJoiner = new StringJoiner(", ");

                    for (int day : freeDays) {
                        stringJoiner.add(String.valueOf(day));
                    }
                    
                    System.out.println("Wpisane dni wolne dla pracownika " +
                            employeeListsMatcher.getEmployee(employee).getFullName() + " to: " + stringJoiner);

                    adding  = !inputHandler.getBoolean("Aby zatwiedzić dni wolne wciśnij 1, aby spróbować ponownie wciśnij 0.");

                    if (!adding ) {
                        for (int day : freeDays) {
                            employeeProposalFreeDays.addEmployeeProposalFreeDay(employeeListsMatcher.getEmployee(employee), day);
                        }
                    }
                }
                else {
                    NotFound.showMessage();
                    adding  = false;
                }
            }
            addProposalFreeDays = inputHandler.getBoolean("Jeśli chcesz dodać kolejne dni wolne innego pracownika wciśnij 1, jeśli nie wciśnij 0.");
        }
        isRunning = false;
        }

    }
}
