package tables;
public class TestDriveInventory{
    private int vehicleID;
    private int customerID;

    public TestDriveInventory(int vehID, int custID){
        this.vehicleID = vehID;
        this.customerID = custID;
    }

    public int getVehicleID(){ return this.vehicleID; }
    public int getCustomerID(){ return this.customerID; }

    public void setVehicleID(int vehid){ this.vehicleID = vehid; }
    public void setCustomerID(int custid){ this.customerID = custid; }
}