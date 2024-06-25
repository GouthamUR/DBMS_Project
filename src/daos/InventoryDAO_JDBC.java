package daos;
import tables.*;
import java.sql.*;

public class InventoryDAO_JDBC implements InventoryDAO{
    private Connection dbConnection;

    public InventoryDAO_JDBC(Connection dbconn){
		dbConnection = dbconn;
	}

    public void addVehicle(int vehID, int quan){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "update inventory set quantity = quantity+(?) where vehicle_id=(?)";
		try {
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, quan);
            preparedStatement.setInt(2, vehID);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Query failed");
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println("Connection could not be closed");
 		}
    }

    public boolean checkPresence(int vehID){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select count(*) from inventory where vehicle_id=(?) and quantity>0";
		try {
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, vehID);
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            if(rs.getInt("count(*)")==0)
                return false;
            else
                return true;
		} catch (SQLException e) {
 			System.out.println("Query failed");
 		}
		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println("Connection could not be closed");
 		}
        return false;
    }

	public void incrementQuantity(int vehID){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "update inventory set quantity = quantity+1 where vehicle_id=(?)";
		try {
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, vehID);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Decrement failed");
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println("Connection could not be closed");
 		}
    }

	public void decrementQuantity(int vehID){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "update inventory set quantity = quantity-1 where vehicle_id=(?)";
		try {
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, vehID);
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Decrement failed");
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println("Connection could not be closed");
 		}
    }
}