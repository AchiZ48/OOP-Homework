package lab8;

import javax.swing.*;
import java.util.*;

abstract class Employee{
    private String firstname;
    private String lastname;
    private String id;
    public Employee(String firstname,String lastname,String id){
        this.firstname=firstname;
        this.lastname=lastname;
        this.id=id;
    }
    public String getFirstname(){return firstname;}
    public String getLastname(){return lastname;}
    public String getId(){return id;}
    public abstract double earning();
    public abstract double bonus(int year);
}
class SalariedEmployee extends Employee{
    private double salary;
    public SalariedEmployee(String firstname,String lastname,String id,double sal){
        super(firstname,lastname,id);
        this.salary=sal;
    }
    @Override public double earning(){return salary*0.95;}
    @Override public double bonus(int year){return year>5?salary*12:salary*6;}
}
class ComEmployee extends Employee{
    private double grossSale;
    private double comRate;
    public ComEmployee(String firstname,String lastname,String id,double sales,double percent){
        super(firstname,lastname,id);
        this.grossSale=sales;
        this.comRate=percent;
    }
    @Override public double earning(){return grossSale+(grossSale*comRate);}
    @Override public double bonus(int year){return year>5?grossSale*6:grossSale*3;}
}

public class lab8_4 {
    public static void printEmp(ArrayList<Employee> a){
        double[] arrayEarn=new double[a.size()];
        double[] arrayBonus=new double[a.size()];
        StringBuilder sb=new StringBuilder();
        sb.append("Fisrt name Last name Earning  Bonus\n");
        for(int i=0;i<a.size();i++){
            Employee r=a.get(i);
            double e=r.earning();
            double b=r.bonus(6);
            arrayEarn[i]=e;
            arrayBonus[i]=b;
            sb.append(r.getFirstname()).append(" ").append(r.getLastname()).append(" ").append(String.format("%.2f",e)).append("  ").append(String.format("%.2f",b)).append("\n");
        }
        JOptionPane.showMessageDialog(null,sb.toString());
    }
    public static void main(String[] args){
        ArrayList<Employee> list=new ArrayList<>();
        list.add(new SalariedEmployee("Ann","Lee","S001",30000));
        list.add(new SalariedEmployee("Bob","Kim","S002",45000));
        list.add(new ComEmployee("Cara","Ng","C001",500000,0.1));
        list.add(new ComEmployee("Dan","Po","C002",250000,0.08));
        printEmp(list);
    }
}