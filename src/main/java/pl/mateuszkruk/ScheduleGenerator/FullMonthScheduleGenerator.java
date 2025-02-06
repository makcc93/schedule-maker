package pl.mateuszkruk.ScheduleGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.mateuszkruk.Schedule.DaysOfWeek;
import pl.mateuszkruk.Schedule.FirstDayAndLenghtOfMonth;

import java.util.ArrayList;
import java.util.List;

@Component
public class FullMonthScheduleGenerator {
    private final SingleDayDraw singleDayDraw;
    private final FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth;
@Autowired
    public FullMonthScheduleGenerator(SingleDayDraw singleDayDraw,
                                      FirstDayAndLenghtOfMonth firstDayAndLenghtOfMonth) {
        this.singleDayDraw = singleDayDraw;
        this.firstDayAndLenghtOfMonth = firstDayAndLenghtOfMonth;
    }

    public void generateFullMonthSchedule() {

        if (PriorityInDraw.getPriority()) {
            List<Integer> weekendDays = new ArrayList<>();
            List<Integer> noneWeekendDays = new ArrayList<>();

            for (int day = 1; day <= firstDayAndLenghtOfMonth.getLengthOfMonth(); day++) {
                DaysOfWeek thisDay = firstDayAndLenghtOfMonth.getMonthSchedule(day);
                if (thisDay.equals(DaysOfWeek.SUNDAY) || thisDay.equals(DaysOfWeek.SATURDAY)) {
                    weekendDays.add(day);
                } else {
                    noneWeekendDays.add(day);
                }
            }

            for (int day : weekendDays) {
                singleDayDraw.drawEmployeesSingleDay(day);
            }

            for (int day : noneWeekendDays) {
                singleDayDraw.drawEmployeesSingleDay(day);
            }
        }
        else {
            for (int day = 1; day <= firstDayAndLenghtOfMonth.getLengthOfMonth(); day++){
                singleDayDraw.drawEmployeesSingleDay(day);
            }
        }


    }
}
