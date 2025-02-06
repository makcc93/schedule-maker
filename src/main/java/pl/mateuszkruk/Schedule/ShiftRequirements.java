package pl.mateuszkruk.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ShiftRequirements {
    Map<Integer, Integer> specificDayRequirements = new HashMap<>();
    FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;

    @Autowired
    public ShiftRequirements(FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth) {
    }

    public void setSpecificDayRequirements(int dayOfMonth, int specificEmployeeRequirements) {
        specificDayRequirements.put(dayOfMonth, specificEmployeeRequirements);
    }

    public int getSpecificDayRequirements(int dayOfMonth) {
        return specificDayRequirements.get(dayOfMonth);
    }

}