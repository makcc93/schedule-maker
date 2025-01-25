package pl.mateuszkruk.WorkTime;

public class StandardMonthlyWorkingHours {
    int standardMonthlyWorkingHours;
    private static StandardMonthlyWorkingHours instance;

    private StandardMonthlyWorkingHours(){}

    public static StandardMonthlyWorkingHours getInstance(){
        if (instance==null){
            instance=new StandardMonthlyWorkingHours();
        }
        return instance;
    }

    public void setStandardMonthlyWorkingHours(int standardMonthlyWorkingHours) {
        this.standardMonthlyWorkingHours = standardMonthlyWorkingHours;
    }
}
