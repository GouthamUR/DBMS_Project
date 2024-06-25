package daos;
import java.sql.*;
import tables.*;
import globals.*;

public class SaleDAO_JDBC implements SaleDAO {
    
    Connection dbConnection;

    public SaleDAO_JDBC(Connection dbconn){
		dbConnection = dbconn;
	}

    @Override
    public void addSale(Sale sale){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into sale(sale_id,vehicle_id,customer_id,employee_id,date_of_sale) values (?,?,?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, sale.get_saleID());
			preparedStatement.setInt(2, sale.get_vehicleID());
			preparedStatement.setInt(3, sale.get_customerID());
			preparedStatement.setInt(4, sale.get_employeeID());
			preparedStatement.setDate(5, sale.get_Date());

			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Add failed1");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
    }

    @Override
    public void updateCount(){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select count(*) from sale";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
 
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				Globals.sales = rs.getInt("count(*)");
		} catch (SQLException e) {
 			System.out.println("Count failed");
 		}
	}
}
