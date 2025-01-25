package pl.mateuszkruk.WorkTime;

public enum OverWorkingHours{
OVER_WORKING_HOURS(0);

private int numberOfOverWorkingHoursPerEmployee=0;
OverWorkingHours (int numberOfOverWorkingHoursPerEmployee){
    this.numberOfOverWorkingHoursPerEmployee=numberOfOverWorkingHoursPerEmployee;
}

    public int getNumberOfOverWorkingHoursPerEmployee() {
        return numberOfOverWorkingHoursPerEmployee;
    }

    public void setNumberOfOverWorkingHoursPerEmployee(int numberOfOverWorkingHoursPerEmployee) {
        this.numberOfOverWorkingHoursPerEmployee = numberOfOverWorkingHoursPerEmployee;
    }
}
