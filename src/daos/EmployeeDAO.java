package daos;
import tables.Employee;

public interface EmployeeDAO {
    public void addEmployee(Employee Employee);
    public void updateCount();
	public void updateSalary(int employeeid,int percent);
	public void listEmployee();
    public float seeRating(int empid);
}
