package tables;
import java.sql.Date;
import globals.*;

public class Sale 
{
    private int saleID;
    private int vehicleID;
    private int customerID;
    private int employeeID;
    private Date date;

    public Sale(int vehId, int custID, int empID){
        saleID = ++Globals.sales;
        vehicleID = vehId;
        customerID = custID;
        employeeID = empID;
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
        this.date = date;
    }

    public int get_saleID(){return this.saleID;}
    public int get_vehicleID() {return this.vehicleID;}
    public int get_customerID() {return this.customerID;}
    public int get_employeeID() {return this.employeeID;}
    public Date get_Date() {return this.date;}
}
