package pl.mateuszkruk.Menu.StepsInMenu;

import pl.mateuszkruk.Employee.Employee;
import pl.mateuszkruk.Employee.EmployeeListsMatcher;
import pl.mateuszkruk.ExportFile.ExportToCSV;
import pl.mateuszkruk.Messages.DefaultInSwitch;
import pl.mateuszkruk.Messages.DrawLine;
import pl.mateuszkruk.Schedule.Shifts;
import pl.mateuszkruk.ScheduleGenerator.FullMonthScheduleGenerator;
import pl.mateuszkruk.ScheduleGenerator.SingleDayDraw;
import pl.mateuszkruk.UserInput.InputHandler;
import pl.mateuszkruk.WorkTime.SumOfMonthlyEmployeeHours;

import java.util.Map;

public class ScheduleGeneratorAndFileExporter {
    private final InputHandler inputHandler;
    private final SingleDayDraw singleDayDraw;
    private final FullMonthScheduleGenerator fullMonthScheduleGenerator;
    private final EmployeeListsMatcher employeeListsMatcher;
    private final SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours;
    public ScheduleGeneratorAndFileExporter(InputHandler inputHandler,
                                            EmployeeListsMatcher employeeListsMatcher,
                                            SumOfMonthlyEmployeeHours sumOfMonthlyEmployeeHours,
                                            SingleDayDraw singleDayDraw,
                                            FullMonthScheduleGenerator fullMonthScheduleGenerator){
        this.inputHandler = inputHandler;
        this.employeeListsMatcher = employeeListsMatcher;
        this.sumOfMonthlyEmployeeHours = sumOfMonthlyEmployeeHours;
        this.singleDayDraw = singleDayDraw;
        this.fullMonthScheduleGenerator = fullMonthScheduleGenerator;
    }
    public void run() {
        DrawLine.draw();

        System.out.println(".:: GENEROWANIE GRAFIKA PRACY ::.");
        boolean isRunning = inputHandler.getBoolean("       Aby wygenerować grafik pracy wedle podanych wcześniej informacji wciśnij 1, jeśli nie wciśnij 0.");
        while (isRunning) {
            fullMonthScheduleGenerator.generateFullMonthSchedule();
            System.out.println("Grafik wygenerowany poprawnie!");


            boolean showSchedule = inputHandler.getBoolean("Jeśli chcesz wyświetlić utworzony grafik wciśnij 1, jeśli nie wciśnij 0.");
            if (showSchedule) {
                showGeneratedSchedule();
            }

            boolean exportSchedule = inputHandler.getBoolean("Jeśli chcesz pobrać grafik wciśnij 1, jeśli nie wciśnij 0.");
            if (exportSchedule) {
                exportFile();
            }

            isRunning = false;
        }
    }

    private void showGeneratedSchedule () {
        for (Map.Entry<Integer, Map<Employee, Shifts>> entry : singleDayDraw.getFinalSchedule().entrySet()) {
            int dayOfMonth = entry.getKey();
            System.out.println();
            System.out.println("DZIEŃ " + dayOfMonth);
            Map<Employee, Shifts> dayMap = entry.getValue();

            for (Map.Entry<Employee, Shifts> secondEntry : dayMap.entrySet()) {
                Employee employee = secondEntry.getKey();
                Shifts shifts = secondEntry.getValue();

                System.out.println(employee.getFullName() + " - " + shifts.getHoursShift());
            }
        }
    }

    private void exportFile(){
        boolean isRunning = true;
        while (isRunning) {
            System.out.println(".:: EXPORT PLIKU ::.");
            System.out.println("[1] Export do pliku .CSV");
            System.out.println("[2] Export do pliku .XLS (Excel)");
            System.out.println("[9] Wyjście");

            int switchChoice = inputHandler.getInt();
            switch (switchChoice) {
                case 1 -> {
                    String fileName = inputHandler.getString("Wpisz nazwę pliku: ");

                    ExportToCSV.exportScheduleToCSV(singleDayDraw.getFinalSchedule(),
                            employeeListsMatcher.getAllEmployees(),
                            sumOfMonthlyEmployeeHours.getMapOfAllEmployeesHours(),
                            singleDayDraw.getDayAndNumberOfEmployees(),
                            fileName);
                }
                case 2 -> {
                    System.out.println("Przepraszamy, funkcjonalność w przygotowaniu. Skorzystaj z exportu .CSV!");
                }
                case 9 -> {
                  isRunning = false;
                }
                default -> {
                    DefaultInSwitch.showMessage();
                }
            }
        }
    }

}