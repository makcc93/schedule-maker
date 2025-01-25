package pl.mateuszkruk.Employee;
import pl.mateuszkruk.UserInput.InputHandler;
public class SpecialEmployeesSet implements EmployeeManagerActions{

    @Override
    public void execute(EmployeeListsMatcher employeeListsMatcher, InputHandler inputHandler) {
        Employee employee1 = new Employee("10005850", "Mateusz", "Kruk", false, true);
        Employee employee2 = new Employee("10005333", "Damian", "Mrozicki", false, true);
        Employee employee4 = new Employee("10004381", "Filip", "Kamiński", true, false);
        Employee employee5 = new Employee("10022760", "Tomasz", "Zając", true, false);
        Employee employee6 = new Employee("10018110", "Martyna", "Nowicka", true, false);
        Employee employee10 = new Employee("10028313", "Honorata", "Kosowska", false, false);
        Employee employee11 = new Employee("10021920", "Michał", "Kozik", false, false);
        Employee employee12 = new Employee("10014069", "Marcin ", "Przepiórka", false, false);
        Employee employee7 = new Employee("10020408", "Agnieszka", "Grajper-Wolak", true, false);
        Employee employee8 = new Employee("10025502", "Katalin", "Jobbagy", true, false);
        Employee employee9 = new Employee("10026100", "Michał", "Woch", true, false);
        Employee employee3 = new Employee("10001795", "Monika", "Baran", false, true);
        Employee employee13 = new Employee("10031234", "Monika", "Szydłowska", false, false);
        Employee employee14 = new Employee("10032133", "Wojciech", "Pietruszka", false, false);

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

        System.out.println("Zestaw pracowników zaczytany poprawnie!");
    }
}
