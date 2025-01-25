package pl.mateuszkruk.Employee;

import pl.mateuszkruk.UserInput.InputHandler;

public interface EmployeeManagerActions {
    void execute(EmployeeListsMatcher employeeListsMatcher, InputHandler inputHandler) throws Exception;

}
