package pl.mateuszkruk.ScheduleGenerator;

public class ScheduleDone {
    static boolean isScheduleDone = false;

    public static void check(boolean isScheduleDone){
        if(isScheduleDone){
            System.out.println("Grafik już został utworzony!");
        }
    }
    public static boolean isScheduleDone() {
        return isScheduleDone;
    }

    public static void setScheduleDone(boolean scheduleDone) {
        isScheduleDone = scheduleDone;
    }
}
