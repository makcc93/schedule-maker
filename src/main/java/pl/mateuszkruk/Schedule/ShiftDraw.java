package pl.mateuszkruk.Schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ShiftDraw {
    private final Random random;

    @Autowired
    public ShiftDraw(Random random) {
        this.random = random;
    }


    public Shifts drawRandomShift() {
        List<Shifts> shifts = Shifts.LIST_OF_SHIFTS;
        return shifts.get(random.nextInt(shifts.size()));
    }


    public Shifts randomAfternoonShift() {
        List<Shifts> afternoonShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isAfternoonShift()) {
                afternoonShifts.add(shifts);
            }
        }

        return afternoonShifts.get(random.nextInt(afternoonShifts.size()));
    }

    public Shifts randomMorningShift() {
        List<Shifts> morningShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isMorningShift()) {
                morningShifts.add(shifts);
            }
        }
        return morningShifts.get(random.nextInt(morningShifts.size()));
    }

    public Shifts randomOpenStoreShift() {
        List<Shifts> openStoreShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isOpenStore()) {
                openStoreShifts.add(shifts);
            }
        }
        return openStoreShifts.get(random.nextInt(openStoreShifts.size()));
    }

    public Shifts randomCloseStoreShift() {
        List<Shifts> closeStoreShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS) {
            if (shifts.isCloseStore()) {
                closeStoreShifts.add(shifts);
            }
        }
        return closeStoreShifts.get(random.nextInt(closeStoreShifts.size()));
    }

    public Shifts randomAllDayShift(){
        List<Shifts> allDayShifts = new ArrayList<>();

        for (Shifts shifts : Shifts.LIST_OF_SHIFTS){
            if (shifts.isMorningShift() && shifts.isAfternoonShift()){
                allDayShifts.add(shifts);
            }
        }
        return allDayShifts.get(random.nextInt((allDayShifts.size())));
    }
}
