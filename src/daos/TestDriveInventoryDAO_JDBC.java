package daos;

import tables.*;
import java.sql.*;

public class TestDriveInventoryDAO_JDBC implements TestDriveInventoryDAO {
    private Connection dbConnection;

    public TestDriveInventoryDAO_JDBC(Connection dbconn){
        this.dbConnection = dbconn;
    }

	@Override
	public boolean findTestDrive(int custID){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select count(*) from test_drive_inventory i where customer_id=(?)";

		try {
            preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, custID);
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
            rs.next();
			if(rs.getInt("count(*)")>0)
				return true;
			else
				return false;
		} catch (SQLException e) {
 			System.out.println("Query failed");
 		}
		return false;
	}

	@Override
    public void vehicleTestDrive(TestDriveInventory tdi){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into test_drive_inventory(vehicle_id,customer_id) values (?,?)";
        try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, tdi.getVehicleID());
			preparedStatement.setFloat(2, tdi.getCustomerID());

 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Add failed1");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
    }

	public void finishTestDrive(int custID){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "delete from test_drive_inventory where customer_id = (?)";
        try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, custID);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Add failed1");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
}
