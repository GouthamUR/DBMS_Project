package tables;
import globals.*;

public class Rating{
    private int sale_id;
    private int employeeID;
    private float rating;

    public Rating(Sale sale,int empID, int rating){
        this.sale_id=sale.get_saleID();
        this.employeeID = empID;
        this.rating = rating;
    }

    public int getSaleID(){ return this.sale_id; }
    public int getEmployeeID(){ return this.employeeID; }
    public float getRating(){ return this.rating; }
}