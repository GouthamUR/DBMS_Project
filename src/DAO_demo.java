import tables.*;
import daos.*;

public class DAO_demo{
    public static DAO_Factory daoFactory;

    public DAO_demo(DAO_Factory dao_Factory){
        this.daoFactory=dao_Factory;
		try{
			this.daoFactory.activateConnection();
		}catch(Exception e){
			System.out.println("Error establishing connection");
		}
    }

	public void update_consts(){

		try{
			// Start transaction boundary
			

			// Carry out DB operations using DAO
			CustomerDAO custdao = daoFactory.getCustomerDAO();
			EmployeeDAO empdao = daoFactory.getEmployeeDAO();
			SaleDAO saledao = daoFactory.getSaleDAO();
			custdao.updateCount();
			empdao.updateCount();
			saledao.updateCount();

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

    public void usecase_cust_insert(Customer customer, String password){
        
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			CustomerDAO custdao = daoFactory.getCustomerDAO();
			CustPassDAO cpdao = daoFactory.getCustPassDAO();
			custdao.addCustomer(customer);
			cpdao.addCustPass(customer, password);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
    }

	public boolean usecase_cust_login(CustPass custPass){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			CustPassDAO cpdao = daoFactory.getCustPassDAO();
			boolean result = cpdao.verifyCustPass(custPass);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );

			return result;
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return false;
	};

	public void usecase_vehicle_list(){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			VehicleDAO vehicleDAO = daoFactory.getVehicleDAO();
			vehicleDAO.listVehicles();

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	};
	
	public void usecase_vehicle_list_admin(){
	try{
		// Start transaction boundary
		// daoFactory.activateConnection();

		// Carry out DB operations using DAO
		VehicleDAO vehicleDAO = daoFactory.getVehicleDAO();
		vehicleDAO.listVehicles_admin();

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	};

	public void usecase_emp_insert(Employee employee, String password){
        
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			EmployeeDAO empdao = daoFactory.getEmployeeDAO();
			EmpPassDAO emppassdao = daoFactory.getEmpPassDAO();
			empdao.addEmployee(employee);
			emppassdao.addEmpPass(employee, password);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
    }
	public boolean usecase_emp_login(EmpPass empPass){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			EmpPassDAO empdao = daoFactory.getEmpPassDAO();
			boolean result = empdao.verifyEmpPass(empPass);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );

			return result;
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return false;
	};

	public void usecase_update_inv(int vehID, int quan){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			VehicleDAO vehicleDAO = daoFactory.getVehicleDAO();
			vehicleDAO.addVehicle(vehID, quan);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public boolean usecase_findTestDriveInventory(int custID){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			TestDriveInventoryDAO testDriveInventoryDAO = daoFactory.getTestDriveInventoryDAO();
			return testDriveInventoryDAO.findTestDrive(custID);
			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return false;
	}

	public void usecase_display_rating(int empid){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			EmployeeDAO empdao = daoFactory.getEmployeeDAO();
			float rating=empdao.seeRating(empid);
			System.out.println(rating);
			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public boolean usecase_checkPresence(int vehID){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			InventoryDAO inventoryDAO = daoFactory.getInventoryDAO();
			return inventoryDAO.checkPresence(vehID);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
		return false;	
	}

	public void usecase_insertTestDrive(TestDriveInventory tdi){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			TestDriveInventoryDAO testDriveInventoryDAO = daoFactory.getTestDriveInventoryDAO();
			InventoryDAO inventoryDAO = daoFactory.getInventoryDAO();
			testDriveInventoryDAO.vehicleTestDrive(tdi);
			inventoryDAO.decrementQuantity(tdi.getVehicleID());
			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public void usecase_deleteTestDrive(int custID){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			TestDriveInventoryDAO testDriveInventoryDAO = daoFactory.getTestDriveInventoryDAO();
			InventoryDAO inventoryDAO = daoFactory.getInventoryDAO();
			testDriveInventoryDAO.finishTestDrive(custID);
			inventoryDAO.incrementQuantity(custID);
			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public void usecase_sell_vehicle(Sale sale){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			SaleDAO saleDAO = daoFactory.getSaleDAO();
			InventoryDAO inventoryDAO = daoFactory.getInventoryDAO();
			saleDAO.addSale(sale);
			inventoryDAO.decrementQuantity(sale.get_vehicleID());
			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public void usecase_insert_rating(Rating rating){
        
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			RatingDAO ratingdao = daoFactory.getRatingDAO();
			ratingdao.addRating(rating);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public void usecase_update_salary(int empid,int percent){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			EmployeeDAO employeedao = daoFactory.getEmployeeDAO();
			employeedao.updateSalary(empid,percent);

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public void usecase_list_employees(){
		try{
			// Start transaction boundary
			// daoFactory.activateConnection();

			// Carry out DB operations using DAO
			EmployeeDAO employeedao = daoFactory.getEmployeeDAO();
			employeedao.listEmployee();

			// End transaction boundary with success
			// daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.COMMIT );
		}
		catch(Exception e){
			// End transaction boundary with failure
			daoFactory.deactivateConnection( DAO_Factory.TXN_STATUS.ROLLBACK );
			e.printStackTrace();
		}
	}

	public void closeConnection(){
		daoFactory.deactivateConnection(DAO_Factory.TXN_STATUS.COMMIT);
	}
}