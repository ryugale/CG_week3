import java.util.ArrayList;
import java.util.List;

interface Department {
    void assignDepartment(String department);

    void getDepartmentDetails();
}

abstract class Employee implements Department{
    protected int employeeId, baseSalary, workingHours;
    private String name;

    
    Employee(int employeeId, int baseSalary, int workingHours, String name) {
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.workingHours = workingHours;
        this.name = name;
        
    }
    abstract public int calculateSalary();
    public void displayDetails(){
        System.out.println("Name: " + name);
        System.out.println("Emp id: " + employeeId);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Working Hours: " + workingHours);
    }

}

class FullTimeEmployee extends Employee {

    String department;
    FullTimeEmployee(int employeeId, int baseSalary, int workingHours, String name) {
        super(employeeId, baseSalary, workingHours, name);
   
    }

    @Override
    public int calculateSalary() {
        return workingHours * 100 * 30;
    }

    @Override
    public void assignDepartment(String department) {
        this.department = department;
    }
    
    @Override
    public void getDepartmentDetails() {
        System.out.println("Department: " + department);
    }
    
}

class PartTimeEmployee extends Employee {
    String department;
    PartTimeEmployee(int employeeId, int baseSalary, String name) {
        super(employeeId, baseSalary, 4, name);
      
    }

    @Override
    public int calculateSalary() {
        return workingHours * 100 * 30;
    }

    @Override
    public void assignDepartment(String department) {
        this.department = department;
    }

    @Override
    public void getDepartmentDetails() {
        System.out.println("Department: " + department);
    }
}

public class EmployeeManagement{
    public static void main(String[] args) {

        List<Employee> empList = new ArrayList<>();

        Employee emp1 = new FullTimeEmployee(1, 30000, 8, "jawahar");
        Employee emp2 = new PartTimeEmployee(2, 15000, "BOB");
        empList.add(emp1);
        emp1.assignDepartment("HR");
        empList.add(emp2);
        emp2.assignDepartment("It");

        for(Employee emp: empList){
            emp.displayDetails();
            emp.getDepartmentDetails();
            System.out.println();
        }
        
    }
}