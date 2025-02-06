package pl.mateuszkruk.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FirstDayAndLenghtOfMonth {

    Map<Integer, DaysOfWeek> monthSchedule = new HashMap<>();
    private DaysOfWeek firstDayOfMonth;
    private final ShiftRequirements shiftRequirements;
    private int lengthOfMonth;


//    @Autowired
//    public FirstDayAndLenghtOfMonth(DaysOfWeek firstDayOfMonth,
//                                    int length) {
//        this.firstDayOfMonth = firstDayOfMonth;
//        this.lengthOfMonth = length;
//        this.shiftRequirements = new ShiftRequirements(this);
//        calculateMonthSchedule();
//        setSpecificDayRequirementsLikeDefault();
//    }

    @Autowired
    public FirstDayAndLenghtOfMonth(){
    this.shiftRequirements = new ShiftRequirements(this);
    }

    public void setMonthDetails(DaysOfWeek firstDayOfMonth,
                                    int length){
        this.firstDayOfMonth = firstDayOfMonth;
        this.lengthOfMonth = length;
        calculateMonthSchedule();
        setSpecificDayRequirementsLikeDefault();
    }



    private void calculateMonthSchedule(){
        DaysOfWeek[] daysOfWeek = DaysOfWeek.values();
        int dayIndex = firstDayOfMonth.ordinal();

        for (int i = 1; i<= lengthOfMonth; i++){
            int dayOfMonthIndex = (dayIndex +(i-1))%7;
            monthSchedule.put(i, daysOfWeek[dayOfMonthIndex]);
        }
    }


    public void setSpecificDayRequirementsLikeDefault(){
        for (int day=1;day <= getLengthOfMonth();day++){
            DaysOfWeek dayOfWeek = monthSchedule.get(day);

            if (dayOfWeek.equals(DaysOfWeek.MONDAY)){
                shiftRequirements.setSpecificDayRequirements(day, DaysOfWeek.MONDAY.getDefaultDayRequirements());
            }

            else if (dayOfWeek.equals(DaysOfWeek.TUESDAY)) {
                shiftRequirements.setSpecificDayRequirements(day, DaysOfWeek.TUESDAY.getDefaultDayRequirements());
            }

            else if (dayOfWeek.equals(DaysOfWeek.WEDNESDAY)) {
                shiftRequirements.setSpecificDayRequirements(day, DaysOfWeek.WEDNESDAY.getDefaultDayRequirements());
            }

            else if (dayOfWeek.equals(DaysOfWeek.THURSDAY)) {
                shiftRequirements.setSpecificDayRequirements(day, DaysOfWeek.THURSDAY.getDefaultDayRequirements());
            }

            else if (dayOfWeek.equals(DaysOfWeek.FRIDAY)) {
                shiftRequirements.setSpecificDayRequirements(day, DaysOfWeek.FRIDAY.getDefaultDayRequirements());
            }

            else if (dayOfWeek.equals(DaysOfWeek.SATURDAY)) {
                shiftRequirements.setSpecificDayRequirements(day, DaysOfWeek.SATURDAY.getDefaultDayRequirements());
            }

            else if (dayOfWeek.equals(DaysOfWeek.SUNDAY)) {
                shiftRequirements.setSpecificDayRequirements(day, DaysOfWeek.SUNDAY.getDefaultDayRequirements());
            }
        }

    }


    public int getLengthOfMonth() {

        return lengthOfMonth;
    }

    public void setLengthOfMonth(int lengthOfMonth) {
        this.lengthOfMonth = lengthOfMonth;
        calculateMonthSchedule();
        setSpecificDayRequirementsLikeDefault();
    }

    public DaysOfWeek getMonthSchedule(int dayOfMonth){
        return monthSchedule.get(dayOfMonth);
    }

    public DaysOfWeek getFirstDayOfMonth() {
        return firstDayOfMonth;
    }

    public ShiftRequirements getShiftRequirements(){
        return shiftRequirements;
    }


    public void setFirstDayOfMonth(DaysOfWeek firstDayOfMonth) {
        this.firstDayOfMonth=firstDayOfMonth;
        calculateMonthSchedule();
        setSpecificDayRequirementsLikeDefault();
    }
}
