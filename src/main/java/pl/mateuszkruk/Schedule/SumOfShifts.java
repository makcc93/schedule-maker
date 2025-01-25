package pl.mateuszkruk.Schedule;

public class SumOfShifts {
    private final int sumOfMorningEmployees;
    private final int sumOfAfternoonEmployees;


    public SumOfShifts(int sumOfMorningEmployees, int sumOfAfternoonEmployees) {
        this.sumOfMorningEmployees = sumOfMorningEmployees;
        this.sumOfAfternoonEmployees = sumOfAfternoonEmployees;
    }

    public int getSumOfMorningEmployees() {
        return sumOfMorningEmployees;
    }

    public int getSumOfAfternoonEmployees() {
        return sumOfAfternoonEmployees;
    }
}
