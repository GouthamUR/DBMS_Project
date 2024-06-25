package daos;
import tables.*;

public interface VehicleDAO {
    public void addVehicle(int vehID, int quan);
    public void listVehicles();
    public void listVehicles_admin();
}
