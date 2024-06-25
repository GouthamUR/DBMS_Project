package daos;
import tables.*;

public interface EmpPassDAO{
    public void addEmpPass(Employee employee,String password);
    public boolean verifyEmpPass(EmpPass empPass);
}