package pl.mateuszkruk.UniversalMethods;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import pl.mateuszkruk.Schedule.DaysOfWeek;

public class WeekendDayChecker {

    public static boolean checkDay(DaysOfWeek thisDay, int dayRequirement, int highRequirement){
        Preconditions.checkArgument(dayRequirement >= 0, "Employees requirement must be a positive value!");
        Preconditions.checkArgument(highRequirement >= 0, "Employees requirement must be a positive value!");

        if (thisDay.equals(DaysOfWeek.SATURDAY) ||
                thisDay.equals(DaysOfWeek.SUNDAY) ||
                dayRequirement >= highRequirement){

            return true;
        }
        else {

            return false;
        }
    }
}
