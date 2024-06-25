import java.util.Scanner;

import globals.Globals;
import tables.*;

public class Main{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        DAO_Factory factory=new DAO_Factory();
        DAO_demo demo=new DAO_demo(factory);
        demo.update_consts();

        System.out.println("******************************************Welcome to GRP Cars******************************************");
        System.out.println("=======================================================================================================");
        while(true){
            System.out.println("LOGIN");
            System.out.println("1.Admin");
            System.out.println("2.Customer");
            System.out.println("3.Employee");
            System.out.println("4.Exit");
            System.out.println("Choose an option:");
            int choice=sc.nextInt();

            if(choice==1){
                System.out.println("Enter admin password:");
                String pass = sc.next();
                while(!pass.equals(Globals.adminPassword)){
                    System.out.println("Password is incorrect, please try again: ");
                    pass = sc.next();
                }
                while(true){
                    System.out.println("Select the action you want to take:");
                    System.out.println("1. Hire employee");
                    System.out.println("2. List vehicles");
                    System.out.println("3. Purchase a vehicle into inventory");
                    System.out.println("4. List employees by average rating");
                    System.out.println("5. Update salary for an employee");
                    System.out.println("6. Exit");

                    int ch=sc.nextInt();
                    if(ch==1){
                        System.out.println("Enter the employee name: ");
                        String empname = sc.next();
                        System.out.println("Enter the salary: ");
                        int sal=sc.nextInt();
                        System.out.println("Enter the employee password: ");
                        String empPass = sc.next();
                        Employee thisE = new Employee(empname,sal);
                        System.out.println("Your Employee ID is: "+thisE.getEmployeeid());
                        demo.usecase_emp_insert(thisE,empPass);                        
                    }
                    else if(ch==2){
                        System.out.println("Listing vehicles in our inventory");
                        demo.usecase_vehicle_list_admin();
                    }
                    else if(ch==3){
                        System.out.println("Enter vehicle id: ");
                        int vehID = sc.nextInt();
                        System.out.println("Enter quantity you want to buy: ");
                        int quan  = sc.nextInt();
                        demo.usecase_update_inv(vehID, quan);
                    }
                    else if(ch==4){
                        System.out.println("List of Employees by rating: ");
                        demo.usecase_list_employees();
                    }
                    else if(ch==5)
                    {
                        System.out.println("Employee Salary Update Page");
                        System.out.println("===========================");
                        System.out.println("Enter the Employee ID:");
                        int sal_empid = sc.nextInt();
                        System.out.println("Enter the % hike in the salary");
                        int sal_hike = sc.nextInt();
                        //Update salary
                        demo.usecase_update_salary(sal_empid, sal_hike);
                        System.out.println("Salary has been updated");
                    }
                    else{
                        break;
                    }
                }
                //Admin login...                    
            }

            else if (choice==2){
                System.out.println("Welcome");
                System.out.println("Are you an existing or new customer?");
                System.out.println("1. Existing");
                System.out.println("2. New");

                int custChoice = sc.nextInt();

                if(custChoice==2){
                    System.out.println("Enter your name: ");
                    String custName = sc.next();
                    System.out.println("Enter your password: ");
                    String pass = sc.next();
                    Customer thisC = new Customer(custName);
                    System.out.println("Your customer ID is: "+thisC.get_customerID());
                    System.out.println("Enter admin password:");
                    String adpass=sc.next();
                    if(adpass.equals(Globals.adminPassword))
                        demo.usecase_cust_insert(thisC,pass);                        
                }

                System.out.println("Please login");
                boolean goHome=false;
                int custid=0;
                while(true){
                    System.out.println("Enter your customer id(enter negative value to exit):");
                    custid=sc.nextInt(); 

                    if(custid<0){
                        goHome = true;
                        break;
                    }
                    System.out.println("Enter your password:");
                    String password=sc.next();
                    CustPass custPass = new CustPass(custid,password);
                    if(demo.usecase_cust_login(custPass)){
                        System.out.println("Login Success");
                        break;
                    }
                    else
                        System.out.println("Try again");
                }

                if(goHome)
                    continue;

                while(true){
                    System.out.println("Select the action you want to take:");
                    System.out.println("1. List available vehicle");
                    System.out.println("2. Take a vehicle to test drive");
                    System.out.println("3. End test drive");
                    System.out.println("4. Purchase a vehicle");
                    System.out.println("5. Exit");
                    custChoice=sc.nextInt();
                    
                    if(custChoice==5){
                        goHome = true;
                        break;
                    }

                    else if(custChoice==1){
                        System.out.println("Listing vehicles in our inventory");
                        demo.usecase_vehicle_list();
                    }

                    else if(custChoice==2){
                        System.out.println("Enter vehicle ID of vehicle you wish to test: ");
                        int vehID = sc.nextInt();
                        if(demo.usecase_findTestDriveInventory(custid)){
                            System.out.println("Sorry, you already have a test drive active");
                            continue;
                        }
                        if(!demo.usecase_checkPresence(vehID)){
                            System.out.println("Sorry, that vehicle is unavailable");
                            continue;
                        }
                        TestDriveInventory newtdi = new TestDriveInventory(vehID, custid);
                        demo.usecase_insertTestDrive(newtdi);
                        System.out.println("Successfully released vehicle to test drive");
                    }

                    else if(custChoice==3){
                        if(demo.usecase_findTestDriveInventory(custid)){
                            demo.usecase_deleteTestDrive(custid);
                            System.out.println("Successfully finished test drive!");
                        }
                        else
                            System.out.println("Looks like you do not have a test drive active!");
                    }

                    else if(custChoice==4){
                        System.out.println("Enter the vehicleID of the vehicle you want to buy:");
                        int vehID=sc.nextInt();
                        System.out.println("Press 1 for confirmation:");
                        int ch=sc.nextInt();
                        if(ch==1){
                            if(!demo.usecase_checkPresence(vehID)) {
                                System.out.println("Vehicle out of stock");
                                continue;
                            }
                            System.out.println("Enter admin password for payment confirmation:");
                            String adpass=sc.next();
                            if(adpass.equals(Globals.adminPassword)){
                                System.out.println("Enter the EmployeeID of the salesman: ");
                                int eid=sc.nextInt();
                                Sale sale=new Sale(vehID,custid,eid);
                                demo.usecase_sell_vehicle(sale);
                                System.out.println("Please rate our service out of 5:");
                                int rated=sc.nextInt();
                                Rating rating=new Rating(sale,eid,rated);
                                demo.usecase_insert_rating(rating);                               
                            }   
                        }                       
                    }
                }

                if(goHome)
                    continue;
            }

            else if (choice==3){
                // System.out.println("Employee Login");
                // System.out.println("=========");
                // System.out.println("Enter Employee ID");
                // int empid = sc.nextInt();
                // System.out.println("Enter password");
                // String empswd = sc.next();

                //Check whether correct username/password
                boolean goHome=false;
                while(true)
                {                    
                    System.out.println("Enter your employee id(enter negative value to exit):");
                    int userid=sc.nextInt(); 
                    if(userid<0){
                        goHome = true;
                        break;
                    }
                    System.out.println("Enter your password:");
                    String password=sc.next();
                    EmpPass empPass = new EmpPass(userid,password);
                    if(demo.usecase_emp_login(empPass)){
                        System.out.println("Login Success");
                        System.out.println("Choose an option:");
                        System.out.println("1.List vehicles");
                        System.out.println("2.Check Rating");
                        int echoice=sc.nextInt();
                        if(echoice==1){
                            System.out.println("Listing vehicles in our inventory");
                            demo.usecase_vehicle_list();
                        }
                        else if(echoice==2){
                            demo.usecase_display_rating(userid);
                        }
            
                        else{
                            goHome = true;
                            break;
                        }
                    }
                    else
                        System.out.println("Try again");
                }

                if(goHome)
                    continue;

            }
            else if (choice==4){break;}
            else{System.out.println("Enter a valid choice:");}
        }
        demo.closeConnection();
        sc.close();
    }
}