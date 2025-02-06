package pl.mateuszkruk.Employee;


public class Employee {
    protected final String sapNumber;
    protected final String firstName;
    protected final String lastName;
    protected final boolean canOperateCredit;
    protected final boolean isManager;



    public Employee(String sapNumber, String firstName, String lastName,
                    boolean canOperateCredit, boolean isManager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sapNumber = sapNumber;
        this.canOperateCredit = canOperateCredit;
        this.isManager = isManager;
    }

    public String getSapNumber() {
        return sapNumber;
    }

    public String getFullName(){
        return firstName +" "+ lastName;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
