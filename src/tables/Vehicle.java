package tables;
public class Vehicle {
    private int vehicleID;
    private String brand;
    private String colour;   
    private int price;

    public int getID(){ return this.vehicleID; }
    public String getBrand() { return this.brand; }
    public String getColour() { return this.colour; }
    public int getPrice() { return this.price; }

    public void setID(int vehID){ this.vehicleID=vehID; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setColour(String colour) { this.colour = colour; }
    public void setPrice(int price) { this.price = price; }

    @Override
    public String toString() {
        String v = "Vehicle ID: "; v += this.vehicleID;
        v+="\n";

        v += "Brand: "; v+=this.brand;
        v+="\n";

        v += "Colour: "; v+=this.colour;
        v+="\n";

        v += "Price: "; v+=this.price;
        return v;
    }
}
