package tables;

public class CustPass {
    int custID;
    String password;

    public CustPass(int id, String pass){
        this.custID = id;
        this.password = pass;
    }

    public int get_customerID(){ return this.custID; }
    public String get_password(){ return this.password; }
}
