package pl.mateuszkruk.Schedule;

public enum DaysOfWeek {
    MONDAY("Poniedziałek", "Pn", 1, 6),
    TUESDAY("Wtorek", "Wt", 2, 5),
    WEDNESDAY("Środa", "Śr", 3, 5),
    THURSDAY("Czwartek", "Czw", 4, 5),
    FRIDAY("Piątek", "Pt", 5, 6),
    SATURDAY("Sobota", "Sb", 6, 7),
    SUNDAY("Niedziela", "Ndz", 7, 0);

    private final String polishName;
    private final String polishShortcut;
    private final int numberOfWeekDay;
    private int defaultDayRequirements;


    DaysOfWeek (String polishName, String polishShortcut, int numberOfWeekDay,
                       int defaultDayRequirements){
        this.polishName = polishName;
        this.polishShortcut = polishShortcut;
        this.numberOfWeekDay = numberOfWeekDay;
        this.defaultDayRequirements = defaultDayRequirements;
    }

    public void setDefaultDayRequirements(int defaultDayRequirements) {
        this.defaultDayRequirements = defaultDayRequirements;
    }

    public int getDefaultDayRequirements() {
        return defaultDayRequirements;
    }

    public String getPolishName() {
        return polishName;
    }

    public String getPolishShortcut() {
        return polishShortcut;
    }

    public int getNumberOfWeekDay() {
        return numberOfWeekDay;
    }

    public static int getSaturdayRequirements(){
        return SATURDAY.getDefaultDayRequirements();
    }

}
