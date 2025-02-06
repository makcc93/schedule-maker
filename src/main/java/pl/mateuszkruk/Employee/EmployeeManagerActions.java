package pl.mateuszkruk.Employee;

import org.springframework.stereotype.Component;
import pl.mateuszkruk.UserInput.InputHandler;
@Component
public interface EmployeeManagerActions {
    void execute(EmployeeListsMatcher employeeListsMatcher, InputHandler inputHandler) throws Exception;

}
