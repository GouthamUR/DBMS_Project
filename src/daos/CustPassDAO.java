package daos;
import tables.*;

public interface CustPassDAO{
    public void addCustPass(Customer customer,String password);
    public boolean verifyCustPass(CustPass custPass);
}