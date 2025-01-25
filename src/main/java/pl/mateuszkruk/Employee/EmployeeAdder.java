package pl.mateuszkruk.Employee;
import pl.mateuszkruk.UserInput.InputHandler;

public class EmployeeAdder implements EmployeeManagerActions {

    @Override
    public void execute(EmployeeListsMatcher employeeListsMatcher, InputHandler inputHandler) throws Exception {
        boolean isContinue = true;

        while (isContinue) {
            System.out.println("Podaj numer SAP pracownika, którego chcesz dodać:");
            String employeeSapNumber = inputHandler.getString();

            if (employeeListsMatcher.getAllEmployees().contains(employeeListsMatcher.getEmployee(employeeSapNumber))) {
                System.out.println("Pracownik o numerze SAP " + employeeSapNumber + " już istnieje!");
            }

            else {
                String firstName = inputHandler.getString("Podaj imię pracownika:");

                String lastName = inputHandler.getString("Podaj nazwisko pracownika:");

                boolean canOperateCredit =
                        inputHandler.getBoolean("Jeśli pracownik może sporządzać wnioski ratalne wciśnij 1, jeśli nie wciśnij 0");

                boolean isManager =
                        inputHandler.getBoolean("Jeśli pracownik jest kierownikiem wciśnij 1, jeśli nie wciśnij 0");

                Employee newEmployee = new Employee(employeeSapNumber, firstName, lastName, canOperateCredit, isManager);
                employeeListsMatcher.addEmployeeToList(newEmployee);
                System.out.println("Pracownik o numerze SAP " + employeeSapNumber + " został dodany prawidłowo!");
                System.out.println("Nowy pracownik: "+newEmployee.getFullName()+", obsługa kredytów: "+newEmployee.canOperateCredit+", kierownik: "+newEmployee.isManager);
                boolean agreement = inputHandler.getBoolean("Jeśli potwiedzasz wciśnij 1, jeśli nie wciśnij 0");

                if(agreement){
                    System.out.println("Pracownik dodany prawidłowo!");
                }
                else{
                    employeeListsMatcher.removeEmployeeFromList(newEmployee);
                }

            }

            isContinue =  inputHandler.getBoolean("Jeśli chcesz dodawać kolejnego pracownika wciśnij 1, jeśli chcesz wyjść wciśnij 0.");
        }
    }
}