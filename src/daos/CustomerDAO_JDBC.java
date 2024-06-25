package daos;
import java.sql.*;
import tables.Customer;
import globals.*;

public class CustomerDAO_JDBC implements CustomerDAO{

    private Connection dbConnection;

    public CustomerDAO_JDBC(Connection dbconn){
		dbConnection = dbconn;
	}

    @Override
	public void addCustomer(Customer customer) {
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into customer(customer_id, customer_name,vehicle_id)values (?,?,null)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setInt(1, customer.get_customerID());
			preparedStatement.setString(2, customer.getCustomerName());
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Add failed");
			System.out.println(e.getMessage());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
 			System.out.println("Connection could not be closed");
 		}
	}

	@Override
	public void updateCount(){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select count(*) from customer";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
 
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				Globals.customers = rs.getInt("count(*)"); 
		} catch (SQLException e) {
 			System.out.println("Count failed");
 		}
	}
}
