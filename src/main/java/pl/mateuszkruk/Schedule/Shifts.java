package pl.mateuszkruk.Schedule;

import java.util.Arrays;
import java.util.List;

public enum Shifts {
    EIGHT_TO_TWENTY("08:00 20:00", 12,true, true,true ,true ),
    NINE_TO_TWENTY("09:00 20:00",11,true,true,false ,true ),
    FOURTEEN_TO_TWENTY("14:00 20:00",6,false, true,false ,true ),
    EIGHT_TO_FOURTEEN("08:00 14:00",6,true, false,true ,false ),
    EIGHT_TO_NINETEEN("08:00 19:00",11,true,true,true ,false ),
    NINE_TO_NINETEEN("09:00 19:00",10,true,true,false,false),
    TEN_TO_TWENTY("10:00 20:00",10,true,true,false,true);


    public final static List<Shifts> LIST_OF_SHIFTS = Arrays.asList(values());

    private final String hoursShift;
    private final int numberOfHours;
    private final boolean morningShift;
    private final boolean afternoonShift;
    private final boolean openStore;
    private final boolean closeStore;

    Shifts(String hoursShift, int numberOfHours, boolean morningShift, boolean afternoonShift, boolean openStore, boolean closeStore){
        this.hoursShift=hoursShift;
        this.numberOfHours=numberOfHours;
        this.morningShift = morningShift;
        this.afternoonShift = afternoonShift;
        this.openStore = openStore;
        this.closeStore = closeStore;
    }
    public boolean isAfternoonShift() {
        return afternoonShift;
    }

    public String getHoursShift() {
        return hoursShift;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public boolean isMorningShift() {
        return morningShift;
    }

    public boolean isOpenStore() {
        return openStore;
    }

    public boolean isCloseStore() {
        return closeStore;
    }

    @Override
    public String toString() {

        return hoursShift;
    }
}
