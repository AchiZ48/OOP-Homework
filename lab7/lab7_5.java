package lab7;

class Employee {
    private String name;
    private String address;
    private double salary;

    public Employee() {}

    public Employee(String name, String address, double salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
    public double getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setSalary(double salary) { this.salary = salary; }

    public String details() {
        return "Employee{name='" + name + "', address='" + address + "', salary=" + salary + "}";
    }

    public void printDetails() {
        System.out.println(details());
    }
}

class Manager extends Employee {
    private String department;
    private String level;
    private double bonus;

    public Manager() {}

    public Manager(String name, String address, double salary,
                   String department, String level, double bonus) {
        super(name, address, salary);
        this.department = department;
        this.level = level;
        this.bonus = bonus;
    }

    public String getDepartment() { return department; }
    public String getLevel() { return level; }
    public double getBonus() { return bonus; }

    public void setDepartment(String department) { this.department = department; }
    public void setLevel(String level) { this.level = level; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    @Override
    public String details() {
        return "Manager{name='" + getName() + "', address='" + getAddress() +
                "', salary=" + getSalary() + ", department='" + department +
                "', level='" + level + "', bonus=" + bonus + "}";
    }
}

public class lab7_5 {
    public static void main(String[] args) {
        Manager emp = new Manager();
        emp.setDepartment("Engineering");
        emp.setLevel("Senior");
        emp.setBonus(25000);

        emp.setName("Achi");
        emp.setAddress("Bangkok, Thailand");
        emp.setSalary(120000);

        emp.printDetails();

        double totalComp = emp.getSalary() + emp.getBonus();
        System.out.println("Total Compensation = " + totalComp);
    }
}

