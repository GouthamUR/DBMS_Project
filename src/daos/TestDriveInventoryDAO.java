package daos;
import tables.*;

public interface TestDriveInventoryDAO {
    public void vehicleTestDrive(TestDriveInventory tdi);
    public boolean findTestDrive(int custID);
	public void finishTestDrive(int custID);
}
