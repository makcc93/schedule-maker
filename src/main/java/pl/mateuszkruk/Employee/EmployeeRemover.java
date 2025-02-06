package pl.mateuszkruk.Employee;
import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.UserInput.InputHandler;

@Component
public class EmployeeRemover implements EmployeeManagerActions {

    @Override
    public void execute(EmployeeListsMatcher employeeListsMatcher, InputHandler inputHandler) throws Exception {
        boolean isContinue = true;

        while (isContinue) {
            String employeeSapNumber = inputHandler.getString("Wpisz numer SAP pracownika, którego chcesz usunąć:");

            if (!employeeListsMatcher.allEmployees.containsKey(employeeSapNumber)) {
                System.out.println("Pracownik o podanym numerze SAP " + employeeSapNumber + " nie istnieje!");
            }

            else {
                System.out.println("Czy na pewno chcesz usunąć pracownika "+ employeeListsMatcher.getEmployee(employeeSapNumber).getFullName()+" SAP "+employeeSapNumber+"?");
                boolean delete = inputHandler.getBoolean("Jeśli usunąć wciśnij 1, jeśli nie wciśnij 0.");

                if (delete){
                employeeListsMatcher.removeEmployeeFromList(employeeListsMatcher.getEmployee(employeeSapNumber));
                System.out.println("Pracownik o podanym numerze SAP " + employeeSapNumber + " został prawidłowo usunięty!");
                }

                else{
                    System.out.println("Pracownik nie został usunięty.");
                }
            }

            isContinue = inputHandler.getBoolean("Jeśli chcesz usuwać kolejnego pracownika wciśnij 1, jeśli chcesz wyjść wciśnij 0.");
        }
    }
}
