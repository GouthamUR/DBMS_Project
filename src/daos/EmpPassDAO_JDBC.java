package daos;
import tables.*;
import java.sql.*;
import globals.*;

public class EmpPassDAO_JDBC implements EmpPassDAO{
    Connection dbConnection;

    public EmpPassDAO_JDBC(Connection dbconn){
		dbConnection = dbconn;
	}

    @Override
	public void addEmpPass(Employee employee,String password) {
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into emp_password (employee_id, pass) values (?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			preparedStatement.setInt(1, employee.getEmployeeid());
			preparedStatement.setString(2, password);
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
 			System.out.println("Add failed2");
			System.out.println("Connection could not be closed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
 		}

		try{
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException ex) {
 			System.out.println("Connection could not be closed");
 		}
    }

	@Override
	public boolean verifyEmpPass(EmpPass empPass){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select pass from emp_password where employee_id = "+empPass.get_employeeID();

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()){
				if(rs.getString("pass").equals(empPass.get_password()))
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
	public void updateCount(){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select count(*) from employee";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
 
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				Globals.employees = rs.getInt("count(*)"); 
		} catch (SQLException e) {
 			System.out.println("Count failed");
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