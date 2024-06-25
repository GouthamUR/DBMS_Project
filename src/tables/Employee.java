package tables;
import globals.Globals;
public class Employee{
    private int employeeID;
    private String emp_name;
    private int salary;

    public Employee(){
    }

    public Employee(String empname,int salary){
        this.employeeID=++Globals.employees;
        this.emp_name=empname;
        this.salary=salary;
    }

    public int getEmployeeid(){return this.employeeID;}
    public String getEmpName(){return this.emp_name;}
    public int getSalary(){return this.salary;}

    public void setEmployeeid(int empid){ this.employeeID = empid;}
    public void setEmpName(String name){ this.emp_name = name;}
    public void setSalary(int sal){ this.salary = sal;}

    public String toString(){
        String e = "Employee id: " + this.employeeID;
        e += "\n";

        e += "Name: " + this.emp_name;
        e += "\n";
        
        e += "Salary: " + this.salary;

        return e;
    }
}