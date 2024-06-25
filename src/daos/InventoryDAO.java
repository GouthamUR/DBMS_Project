package daos;
import tables.*;

public interface InventoryDAO {
    public void addVehicle(int vehID, int quantity);
    public boolean checkPresence(int vehID);
    public void incrementQuantity(int vehID);
    public void decrementQuantity(int vehID);
}
