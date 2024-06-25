package daos;
import tables.*;
import java.sql.*;


public class CustPassDAO_JDBC implements CustPassDAO{
    Connection dbConnection;

    public CustPassDAO_JDBC(Connection dbconn){
		dbConnection = dbconn;
	}

    @Override
	public void addCustPass(Customer customer,String password) {
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into custpassword(customer_id, pass)values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setInt(1, customer.get_customerID());
			preparedStatement.setString(2, password);
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Add failed");
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
	public boolean verifyCustPass(CustPass custPass){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select pass from custpassword where customer_id = "+custPass.get_customerID();

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				if(rs.getString("pass").equals(custPass.get_password()))
					return true;
			}
			return false;
		} catch (SQLException ex) {
 			System.out.println("Query failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
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
}