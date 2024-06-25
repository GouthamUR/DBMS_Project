package tables;

public class EmpPass {
    int empID;
    String password;

    public EmpPass(int id, String pass){
        this.empID = id;
        this.password = pass;
    }

    public int get_employeeID(){ return this.empID; }
    public String get_password(){ return this.password; }
}
