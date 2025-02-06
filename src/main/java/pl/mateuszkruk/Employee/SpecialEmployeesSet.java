package pl.mateuszkruk.Employee;
import org.springframework.stereotype.Service;
import pl.mateuszkruk.UserInput.InputHandler;
@Service
public class SpecialEmployeesSet implements EmployeeManagerActions{

    @Override
    public void execute(EmployeeListsMatcher employeeListsMatcher, InputHandler inputHandler) {
        Employee employee1 = new Employee("1", "Mateusz", "Nowak", false, true);
        Employee employee2 = new Employee("2", "Damian", "Kowalski", false, true);
        Employee employee4 = new Employee("3", "Filip", "Wiśniewski", true, false);
        Employee employee5 = new Employee("4", "Tomasz", "Dąb", true, false);
        Employee employee6 = new Employee("5", "Martyna", "Koza", true, false);
        Employee employee10 = new Employee("6", "Honorata", "Lisowska", false, false);
        Employee employee11 = new Employee("7", "Michał", "Dzikowski", false, false);
        Employee employee12 = new Employee("8", "Marcin ", "Niebo", false, false);
        Employee employee7 = new Employee("9", "Agnieszka", "Gaj", true, false);
        Employee employee8 = new Employee("10", "Katarzyna", "Janiszek", true, false);
        Employee employee9 = new Employee("11", "Michał", "Guz", true, false);
        Employee employee3 = new Employee("12", "Monika", "Sowa", false, true);
        Employee employee13 = new Employee("13", "Monika", "Noworolnik", false, false);
        Employee employee14 = new Employee("14", "Wojciech", "Jakubowski", false, false);

        employeeListsMatcher.addEmployeeToList(employee1);
        employeeListsMatcher.addEmployeeToList(employee2);
        employeeListsMatcher.addEmployeeToList(employee3);
        employeeListsMatcher.addEmployeeToList(employee4);
        employeeListsMatcher.addEmployeeToList(employee5);
        employeeListsMatcher.addEmployeeToList(employee6);
        employeeListsMatcher.addEmployeeToList(employee7);
        employeeListsMatcher.addEmployeeToList(employee8);
        employeeListsMatcher.addEmployeeToList(employee9);
        employeeListsMatcher.addEmployeeToList(employee10);
        employeeListsMatcher.addEmployeeToList(employee11);
        employeeListsMatcher.addEmployeeToList(employee12);
        employeeListsMatcher.addEmployeeToList(employee13);
        employeeListsMatcher.addEmployeeToList(employee14);

        System.out.println("Zestaw pracowników zaczytany poprawnie!" +
                System.lineSeparator());
    }
}
