package pl.mateuszkruk.UniwersalMethods;

import org.junit.jupiter.api.Test;
import pl.mateuszkruk.Schedule.DaysOfWeek;
import pl.mateuszkruk.UniversalMethods.WeekendDayChecker;

import static org.junit.jupiter.api.Assertions.*;

public class WeekendDayCheckerTest {

    @Test
    public void isWorking(){
        int dayRequirement = 6;
        int highRequirement = 7;
        DaysOfWeek dayOfWeek = DaysOfWeek.SUNDAY;

        boolean isWeekendDay = WeekendDayChecker.checkDay(dayOfWeek,dayRequirement,highRequirement);

        assertTrue(isWeekendDay);
    }

    @Test
    public void nullCheck(){
        int dayRequirement = 6;
        int highRequirement = 7;
        DaysOfWeek dayOfWeek = null;

        assertThrows(NullPointerException.class,() -> WeekendDayChecker.checkDay(dayOfWeek, dayRequirement, highRequirement));
    }

    @Test
    public void negativeValues(){
        int dayRequirement = -1;
        int highRequirement = -1;
        DaysOfWeek dayOfWeek = DaysOfWeek.SUNDAY;

        assertThrows(IllegalArgumentException.class,() -> WeekendDayChecker.checkDay(dayOfWeek, dayRequirement, highRequirement));
    }
}
