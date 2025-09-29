package lab2;

import java.util.Date;

public class lab2_6 {
    public static void main(String[] args) {
        Account acc = new Account(1122, 20000);
        acc.setAnnualInterestRate(4.5); // 4.5%

        acc.withdraw(2500);

        acc.deposit(3000);

        System.out.println("Account ID: " + acc.getId());
        System.out.println("Balance: " + acc.getBalance());
        System.out.println("Monthly Interest Rate: " + acc.getMonthlyInterestRate() + " %");
        System.out.println("Monthly Interest: " + acc.getMonthlyInterest());
        System.out.println("Date Created: " + acc.getDateCreated());
    }
}
class Account {
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    // Default constructor
    public Account() {
        this.dateCreated = new Date();
    }

    // Constructor with id and balance
    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    // Accessor and Mutator methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate() / 100;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}