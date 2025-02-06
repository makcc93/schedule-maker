package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Schedule.DaysOfWeek;
@Component
public class WeekendDayChecker {

    public static boolean checkDay(DaysOfWeek thisDay, int dayRequirement, int highRequirement){
        Preconditions.checkArgument(dayRequirement >= 0, "Employees requirement must be a positive value!");
        Preconditions.checkArgument(highRequirement >= 0, "Employees requirement must be a positive value!");
        Preconditions.checkNotNull(thisDay,"DaysOfWeek day cannot be null!");

        return thisDay.equals(DaysOfWeek.SATURDAY) ||
                thisDay.equals(DaysOfWeek.SUNDAY) ||
                dayRequirement >= highRequirement;
    }
}
