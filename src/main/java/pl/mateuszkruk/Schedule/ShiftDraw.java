package pl.mateuszkruk.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShiftDraw {
    private static final Random random = new Random();

    public static Shifts drawRandomShift() {
        List<Shifts> shifts = Shifts.LIST_OF_SHIFTS;
        return shifts.get(random.nextInt(shifts.size()));
    }


    public static Shifts randomAfternoonShift() {
        List<Shifts> afternoonShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isAfternoonShift()) {
                afternoonShifts.add(shifts);
            }
        }

        return afternoonShifts.get(random.nextInt(afternoonShifts.size()));
    }

    public static Shifts randomMorningShift() {
        List<Shifts> morningShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isMorningShift()) {
                morningShifts.add(shifts);
            }
        }
        return morningShifts.get(random.nextInt(morningShifts.size()));
    }

    public static Shifts randomOpenStoreShift() {
        List<Shifts> openStoreShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isOpenStore()) {
                openStoreShifts.add(shifts);
            }
        }
        return openStoreShifts.get(random.nextInt(openStoreShifts.size()));
    }

    public static Shifts randomCloseStoreShift() {
        List<Shifts> closeStoreShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isCloseStore()) {
                closeStoreShifts.add(shifts);
            }
        }
        return closeStoreShifts.get(random.nextInt(closeStoreShifts.size()));
    }

    public static Shifts randomAllDayShift(){
        List<Shifts> allDayShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS){
            if (shifts.isMorningShift() && shifts.isAfternoonShift()){
                allDayShifts.add(shifts);
            }
        }
        return allDayShifts.get(random.nextInt((allDayShifts.size())));
    }
}
