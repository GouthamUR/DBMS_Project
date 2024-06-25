package daos;
import java.sql.*;
import tables.Employee;
import globals.*;


public class EmployeeDAO_JDBC implements EmployeeDAO{

    Connection dbConnection;

    public EmployeeDAO_JDBC(Connection dbconn){
		dbConnection = dbconn;
	}

    @Override
	public void addEmployee(Employee employee) {
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "insert into employee(employee_id,employee_name,salary) values (?,?,?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, employee.getEmployeeid());
			preparedStatement.setString(2, employee.getEmpName());
			preparedStatement.setInt(3, employee.getSalary());

 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Add failed1");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
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
	}

	public float seeRating(int empid){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select e.employee_id,e.employee_name,avg(rating) from employee e inner join rating r on e.employee_id=r.employee_id where e.employee_id=(?) group by employee_id";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, empid);
 
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
				return rs.getFloat("avg(rating)");
		} catch (SQLException e) {
 			System.out.println("Count failed");
 		}
		return 0;
	}

	public void listEmployee(){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "select e.employee_id,e.employee_name,e.salary,avg(rating) from employee e inner join rating r on e.employee_id=r.employee_id group by employee_id order by avg(rating) desc";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
 
			// execute insert SQL stetement
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				Employee thisEmployee = new Employee();
				thisEmployee.setEmployeeid(rs.getInt("employee_id"));
				thisEmployee.setEmpName(rs.getString("employee_name"));
				thisEmployee.setEmployeeid(rs.getInt("salary"));

				System.out.println("+-+-+-+-+-+");
				System.out.println(thisEmployee);
				System.out.println("Average rating: " + rs.getFloat("avg(rating)"));
				System.out.println("+-+-+-+-+-+");

			}
		} catch (SQLException e) {
 			System.out.println("Count failed");
 		}
	}

	public void updateSalary(int employeeid,int percent){
		PreparedStatement preparedStatement = null;																																																																																																																																													
		String sql;
		sql = "update employee set salary=salary+salary*((?)/100) where employee_id=(?)";

		try {
			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setInt(1, percent);
			preparedStatement.setInt(2, employeeid);
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
 			System.out.println("Count failed");
 		}
	}
}
