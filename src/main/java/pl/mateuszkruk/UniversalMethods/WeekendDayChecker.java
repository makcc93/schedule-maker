package pl.mateuszkruk.UniversalMethods;

import pl.mateuszkruk.Schedule.DaysOfWeek;

public class WeekendDayChecker {

    public static boolean checkDay(DaysOfWeek thisDay, int dayRequirement, int highRequirement){
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
