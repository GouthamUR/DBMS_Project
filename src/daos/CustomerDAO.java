package daos;
import tables.Customer;

public interface CustomerDAO {
    public void addCustomer(Customer customer);
    public void updateCount();
}
