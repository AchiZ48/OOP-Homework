package lab6.lab6_5;

import java.util.Date;

class Account {
    private int accountNumber;
    private double balance;
    private double annualInterestRate = 4.5;
    private Date dateCreated;

    public Account() {
        this.accountNumber = 0;
        this.balance = 0.0;
        this.dateCreated = new Date();
    }

    public Account(int accountNumber, double balance, Date dateCreated) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dateCreated = dateCreated;
    }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public Date getDateCreated() { return dateCreated; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public double getAnnualInterestRate() { return annualInterestRate; }
    public void setAnnualInterestRate(double annualInterestRate) { this.annualInterestRate = annualInterestRate; }

    public double getMonthlyInterestRate() { return annualInterestRate/12; }


    public void withdraw(double amount){
        if(this.balance >= amount){
            this.balance -= amount;
        }
    }
    public void deposit(double amount){
            this.balance += amount;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               "\nBalance: " + balance +
               "\nDate Created: " + dateCreated;
    }
}

class Application {
    public static void main(String[] args) {
        Date openDate1 = new Date();

        Account acc1 = new Account(1122, 20000.0, openDate1);
        System.out.println(acc1.getBalance());
        System.out.println(acc1.getMonthlyInterestRate());
        acc1.withdraw(2500);
        acc1.deposit(3000);
        System.out.println(acc1.getBalance());



    }
}
