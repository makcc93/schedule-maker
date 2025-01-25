package pl.mateuszkruk.Schedule;
import java.util.HashMap;
import java.util.Map;

public class ShiftRequirements {
    Map<Integer, Integer> specificDayRequirements = new HashMap<>();

    public ShiftRequirements(FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth) {
    }

    public void setSpecificDayRequirements(int dayOfMonth, int specificEmployeeRequirements) {
        specificDayRequirements.put(dayOfMonth, specificEmployeeRequirements);
    }

    public int getSpecificDayRequirements(int dayOfMonth) {
        return specificDayRequirements.get(dayOfMonth);
    }

}