import java.lang.*;
import java.sql.*;
import tables.*;
import daos.*;
/*
	Methods to be called in the following order:

	1. activateConnection
	2. 	Any number getDAO calls with any number of database transactions
	3. deactivateConnection
*/
public class DAO_Factory{

	public enum TXN_STATUS { COMMIT, ROLLBACK };

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectdb?characterEncoding=latin1&useConfigs=maxPerformance";
	static final String USER = "root";
	static final String PASS = "password";
	private Connection dbconnection = null;

	CustomerDAO customerDAO = null;
	CustPassDAO custPassDAO = null;
	VehicleDAO vehicleDAO = null;
	EmployeeDAO employeeDAO = null;
	EmpPassDAO empPassDAO = null;
	RatingDAO ratingDAO = null;
	InventoryDAO inventoryDAO = null;
	TestDriveInventoryDAO testDriveInventoryDAO = null;
	SaleDAO saleDAO = null;

	boolean activeConnection = false;

	public DAO_Factory()
	{
		this.dbconnection = null;
		activeConnection = false;
	}

	public void activateConnection() throws Exception
	{
		if( activeConnection == true && dbconnection==null)
			throw new Exception("Connection already active");

		System.out.println("Connecting to database...");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			dbconnection = DriverManager.getConnection(DB_URL,USER,PASS);
			dbconnection.setAutoCommit(false);

			activeConnection = true;
		} catch(ClassNotFoundException ex) {
			System.out.println("Error: unable to load driver class!");
			System.exit(1);
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public CustomerDAO getCustomerDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( customerDAO == null )
			customerDAO = new CustomerDAO_JDBC( dbconnection );

		return customerDAO;
	}

	public CustPassDAO getCustPassDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( custPassDAO == null )
			custPassDAO = new CustPassDAO_JDBC( dbconnection );

		return custPassDAO;
	}

	public VehicleDAO getVehicleDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( vehicleDAO == null )
			vehicleDAO = new VehicleDAO_JDBC( dbconnection );

		return vehicleDAO;
	}

	public EmployeeDAO getEmployeeDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( employeeDAO == null )
			employeeDAO = new EmployeeDAO_JDBC( dbconnection );

		return employeeDAO;
	}

	public EmpPassDAO getEmpPassDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( empPassDAO == null )
			empPassDAO = new EmpPassDAO_JDBC( dbconnection );

		return empPassDAO;
	}

	public RatingDAO getRatingDAO() throws Exception
	{
		if( activeConnection == false )
		throw new Exception("Connection not activated...");

		if( ratingDAO == null )
			ratingDAO = new RatingDAO_JDBC( dbconnection );

		return ratingDAO;
	}

	public InventoryDAO getInventoryDAO() throws Exception
	{
		if( activeConnection == false )
		throw new Exception("Connection not activated...");

		if( inventoryDAO == null )
			inventoryDAO = new InventoryDAO_JDBC( dbconnection );

		return inventoryDAO;
	}

	public TestDriveInventoryDAO getTestDriveInventoryDAO() throws Exception
	{
		if( activeConnection == false )
		throw new Exception("Connection not activated...");

		if( testDriveInventoryDAO == null )
			testDriveInventoryDAO = new TestDriveInventoryDAO_JDBC( dbconnection );

		return testDriveInventoryDAO;
	}

	public SaleDAO getSaleDAO() throws Exception
	{
		if( activeConnection == false )
			throw new Exception("Connection not activated...");

		if( saleDAO == null )
			saleDAO = new SaleDAO_JDBC( dbconnection );

		return saleDAO;
	}


	public void deactivateConnection( TXN_STATUS txn_status )
	{
		// Okay to keep deactivating an already deactivated connection
		activeConnection = false;
		if( dbconnection != null ){
			try{
				if( txn_status == TXN_STATUS.COMMIT )
					dbconnection.commit();
				else
					dbconnection.rollback();

				dbconnection.close();
				dbconnection = null;

				// Nullify all DAO objects
				customerDAO = null;
				custPassDAO = null;
				vehicleDAO = null;
				employeeDAO = null;
				ratingDAO = null;
				empPassDAO = null;
				ratingDAO = null;
				inventoryDAO = null;
				saleDAO = null;
				testDriveInventoryDAO = null;
			}
			catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
	}
};
