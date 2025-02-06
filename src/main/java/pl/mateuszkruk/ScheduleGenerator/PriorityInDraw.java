package pl.mateuszkruk.ScheduleGenerator;

import org.springframework.stereotype.Component;

@Component
public class PriorityInDraw {

  public static boolean priority;

    public static void setPriority(boolean priority) {
        PriorityInDraw.priority = priority;
    }

    public static boolean getPriority() {
        return priority;
    }
}
