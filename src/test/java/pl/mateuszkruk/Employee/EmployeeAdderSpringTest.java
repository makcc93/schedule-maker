package pl.mateuszkruk.Employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mateuszkruk.UserInput.InputHandler;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeAdderSpringTest {

    @Mock
    private InputHandler inputHandler;

    @Mock
    private EmployeeListsMatcher employeeListsMatcher;

    @InjectMocks
    private EmployeeAdder employeeAdder;

    @Test
    public void executeTestAddNewEmployee() throws Exception {
        when(inputHandler.getString("Podaj numer SAP pracownika, którego chcesz dodać:")).thenReturn("123");

        when(employeeListsMatcher.getAllEmployees().contains(employeeListsMatcher.getEmployee("123"))).thenReturn(null);

        when(inputHandler.getString("Podaj imię pracownika:")).thenReturn("Tom");

        when(inputHandler.getString("Podaj nazwisko pracownika:")).thenReturn("Stone");

        when(inputHandler.getBoolean("Jeśli pracownik może sporządzać wnioski ratalne wciśnij 1, jeśli nie wciśnij 0")).thenReturn(true);

        when(inputHandler.getBoolean("Jeśli pracownik jest kierownikiem wciśnij 1, jeśli nie wciśnij 0")).thenReturn(false);

        when(inputHandler.getBoolean("Jeśli potwiedzasz wciśnij 1, jeśli nie wciśnij 0")).thenReturn(true);

        when(inputHandler.getBoolean("Jeśli chcesz dodawać kolejnego pracownika wciśnij 1, jeśli chcesz wyjść wciśnij 0.")).thenReturn(false);

        employeeAdder.execute(employeeListsMatcher,inputHandler);

        verify(employeeListsMatcher).addEmployeeToList(any(Employee.class));
        verify(employeeListsMatcher, never()).removeEmployeeFromList(any(Employee.class));

    }

    @Test
    public void executeTestWithEmptyEmployeeValues() throws Exception{
        when(inputHandler.getString("Podaj numer SAP pracownika, którego chcesz dodać:"))
                .thenReturn("")
                .thenReturn("123");

        when(employeeListsMatcher.getAllEmployees().contains(employeeListsMatcher.getEmployee("")))
                .thenReturn(null);

        when(inputHandler.getString("Podaj imię pracownika:"))
                .thenReturn("")
                .thenReturn("Adam");

        when(inputHandler.getString("Podaj nazwisko pracownika:"))
                .thenReturn("")
                .thenReturn("Smith");

        when(inputHandler.getBoolean("Jeśli pracownik może sporządzać wnioski ratalne wciśnij 1, jeśli nie wciśnij 0"))
                .thenReturn(true);

        when(inputHandler.getBoolean("Jeśli pracownik jest kierownikiem wciśnij 1, jeśli nie wciśnij 0"))
                .thenReturn(false);

        when(inputHandler.getBoolean("Jeśli potwiedzasz wciśnij 1, jeśli nie wciśnij 0"))
                .thenReturn(true);

        when(inputHandler.getBoolean("Jeśli chcesz dodawać kolejnego pracownika wciśnij 1, jeśli chcesz wyjść wciśnij 0."))
                .thenReturn(false);

        employeeAdder.execute(employeeListsMatcher,inputHandler);

        verify(employeeListsMatcher,never()).removeEmployeeFromList(any(Employee.class));
    }
}
