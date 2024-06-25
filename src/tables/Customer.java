package tables;
import globals.Globals;

public class Customer{
    private int customer_id;
    private String customer_name;
    private int vehicleID;

    public int get_customerID(){return this.customer_id;}
    public String getCustomerName(){return this.customer_name;}
    public int get_vehicleID(){return this.vehicleID;}

    public Customer(String custName){
        customer_id=++Globals.customers;
        customer_name=custName;
        // vehicleID=null;
    }
}