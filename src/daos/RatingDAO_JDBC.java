package daos;
import tables.*;
import java.sql.*;
import globals.*;


public class RatingDAO_JDBC implements RatingDAO{
    Connection dbConnection;
    
    public RatingDAO_JDBC(Connection dbconn){
        this.dbConnection = dbconn;
    }
    
    public void addRating(Rating rating){
        PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into rating(sale_id,employee_id,rating) values (?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, rating.getSaleID());
			preparedStatement.setInt(2, rating.getEmployeeID());
			preparedStatement.setFloat(3, rating.getRating());

 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Add failed");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}
}
