/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private String firstname;
    private String lastname;
    private String id;
    private double salary;
    
    public Employee(String firstname, String lastname, String id, double sal) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
        this.salary = sal;
    }

    public double earning() {
        return salary * 0.95;
    }

    public double bonus(int year) {
        if (year > 5) {
            return salary * 12; 
        } else {
            return salary * 6; 
        }
    }

    public String getFirstname() {
        return firstname;
    }
public String getLastname() {
        return lastname;
    }
}

public class Lab5No5 {
    public static void printEmp(ArrayList<Employee> a, ArrayList<Integer> years) {
        System.out.printf("%-12s %-12s %-10s %-10s\n", "First name", "Last name", "Earning", "Bonus");
        for (int i = 0; i < a.size(); i++) {
            Employee emp = a.get(i);
            System.out.printf("%-12s %-12s %-10.2f %-10.2f\n",
                    emp.getFirstname(),
                    emp.getLastname(),
                    emp.earning(),
                    emp.bonus(years.get(i)));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Employee> arrayEarn = new ArrayList<>(); 
        ArrayList<Integer> yearList = new ArrayList<>(); 

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();      

        for (int i = 0; i < n; i++) {
            System.out.println("Employee #" + (i + 1));
            System.out.print("First name: ");
            String fname = sc.nextLine();

            System.out.print("Last name: ");
            String lname = sc.nextLine();

            System.out.print("Employee ID: ");
            String id = sc.nextLine();

            System.out.print("Salary: ");
            double salary = sc.nextDouble();

            System.out.print("Years worked: ");
            int year = sc.nextInt();
            sc.nextLine(); // Clear buffer

            arrayEarn.add(new Employee(fname, lname, id, salary));
            yearList.add(year);
        }

        System.out.println("\nResult:");
        printEmp(arrayEarn, yearList);
    }
}
