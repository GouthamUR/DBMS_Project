package daos;
import java.sql.*;
import tables.*;

public class VehicleDAO_JDBC implements VehicleDAO{
    Connection dbConnection;

    public VehicleDAO_JDBC(Connection dbconn){
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

    public void listVehicles(){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select * from inventory i inner join vehicle v on i.vehicle_id=v.vehicle_id where i.quantity>0";

		try {
            preparedStatement = dbConnection.prepareStatement(sql);
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Vehicle thisVehicle = new Vehicle();
                thisVehicle.setID(rs.getInt("vehicle_id"));
                thisVehicle.setBrand(rs.getString("brand"));
                thisVehicle.setColour(rs.getString("colour"));
                thisVehicle.setPrice(rs.getInt("price"));
                int q = rs.getInt("quantity");

                System.out.println("-----------------");
                System.out.println(thisVehicle);
				
                System.out.println("Quantity: "+q);
                System.out.println("-----------------");
            }
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

	public void listVehicles_admin(){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select * from inventory i inner join vehicle v on i.vehicle_id=v.vehicle_id";

		try {
            preparedStatement = dbConnection.prepareStatement(sql);
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Vehicle thisVehicle = new Vehicle();
                thisVehicle.setID(rs.getInt("vehicle_id"));
                thisVehicle.setBrand(rs.getString("brand"));
                thisVehicle.setColour(rs.getString("colour"));
                thisVehicle.setPrice(rs.getInt("price"));
                int q = rs.getInt("quantity");

                System.out.println("-----------------");
                System.out.println(thisVehicle);
                
                if(q==0)
                    System.out.println("This vehicle is out of stock.");
                else
                    System.out.println("Quantity: "+q);
                System.out.println("-----------------");
            }
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
}
