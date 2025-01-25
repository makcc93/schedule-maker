package pl.mateuszkruk.Menu.StepsInMenu;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.Messages.DefaultInSwitch;
import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.Messages.NotFound;
import pl.mateuszkruk.Schedule.FirstDayAndLenghtOfMonth;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.UserInput.InputHandler;
import pl.mateuszkruk.WorkTime.SpecificShiftToEmployeeAdder;

import java.util.List;

public class SetSpecificShiftToEmployee {
    private final InputHandler inputHandler;
    private final SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder;
    private final EmployeeListsMatcher employeeListsMatcher;
    private FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;
    private final String eightToFourteen = "08:00 14:00";
    private final String fourteenToTwenty = "14:00 20:00";
    private final String eightToTwenty = "08:00 20:00";

    public SetSpecificShiftToEmployee(InputHandler inputHandler,
                                      SpecificShiftToEmployeeAdder specificShiftToEmployeeAdder,
                                      EmployeeListsMatcher employeeListsMatcher,
                                      FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth) {
        this.inputHandler = inputHandler;
        this.specificShiftToEmployeeAdder = specificShiftToEmployeeAdder;
        this.employeeListsMatcher = employeeListsMatcher;
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
    }

    public void run() {
        DrawLine.draw();
        int step = 7;
        System.out.println(".:: KROK " + step + " WPISANIE DANEMU PRACOWNIKOWI, KONKRETNEJ ZMIANY ::.");

        boolean wannaAdd = inputHandler.getBoolean("        Jeśli chcesz dodać konkretną zmianę danemu pracownikowy wciśnij 1, jeśli nie wciśnij 0.");

        while (wannaAdd) {
            String employeeBySAP = inputHandler.getString("Wpisz numer SAP pracownika:");
            Employee employee = employeeListsMatcher.getEmployee(employeeBySAP);
            if (employeeListsMatcher.getAllEmployees().contains(employee)) {
                boolean stillChoosing = true;

                while (stillChoosing) {
                    System.out.println("Wybrany pracownik: " + employee.getFullName());

                    int userChoice = inputHandler.getInt("""
                         Wybierz zmianę, którą chcesz wprowadzić dla tego pracownika:
                            [1] 8:00 14:00
                            [2] 14:00 20:00
                            [3] 08:00 20:00
                            
                            [9] Wróć
                            """);

                    switch (userChoice) {
                        case 1 -> {
                            System.out.println(eightToFourteen);
                            List<Integer> eightToFourteenDays = inputHandler.getListOfIntegers();

                                for (int dayOfMonth : eightToFourteenDays) {
                                    specificShiftToEmployeeAdder.add(employee,
                                        Shifts.EIGHT_TO_FOURTEEN,
                                        dayOfMonth);
                                }
                            System.out.println("Poprawnie dodano zmianę " + eightToFourteen + " dla " + employee.getFullName() +
                                    " w dniach: " + eightToFourteenDays +
                                    System.lineSeparator());
                        }

                        case 2 -> {
                            System.out.println(fourteenToTwenty);
                            List<Integer> fourteenToTwentyDays = inputHandler.getListOfIntegers();

                                for (int dayOfMonth : fourteenToTwentyDays) {
                                    specificShiftToEmployeeAdder.add(employee,
                                        Shifts.FOURTEEN_TO_TWENTY,
                                        dayOfMonth);
                                }
                            System.out.println("Poprawnie dodano zmianę " + fourteenToTwenty + " dla " + employee.getFullName() +
                                    " w dniach: " + fourteenToTwentyDays +
                                    System.lineSeparator());
                        }

                        case 3 -> {
                            System.out.println(eightToTwenty);
                            List<Integer> eightToTwentyDays = inputHandler.getListOfIntegers();

                                for (int dayOfMonth : eightToTwentyDays) {
                                    specificShiftToEmployeeAdder.add(employee,
                                        Shifts.EIGHT_TO_TWENTY,
                                        dayOfMonth);
                                }
                            System.out.println("Poprawnie dodano zmianę " + eightToTwenty + " dla " + employee.getFullName() +
                                    " w dniach: " + eightToTwentyDays +
                                    System.lineSeparator());
                        }

                        case 9 -> {
                            stillChoosing = false;
                        }
                        default -> {
                            DefaultInSwitch.showMessage();
                        }
                    }
                }

                wannaAdd = inputHandler.getBoolean("Jeśli chcesz dodać kolejną zmianę wciśnij 1, jeśli nie wciśnij 0.");

            } else {
                NotFound.showMessage();
            }
        }
    }
}
