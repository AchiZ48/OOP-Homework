class Date {
    private int day;
    private int month;  // เปลี่ยนจาก String เป็น int
    private int year;

    public Date() {
        this.day = 1;
        this.month = 1;  // January = 1
        this.year = 2000;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                           "July", "August", "September", "October", "November", "December"};
        String monthName = (month >= 1 && month <= 12) ? months[month - 1] : "InvalidMonth";
        return day + " " + monthName + " " + year;
    }

    public int monthsBetween(Date other) {
        int yearDiff = other.getYear() - this.year;
        int monthDiff = other.getMonth() - this.month;
        int dayDiff = other.getDay() - this.day;

        int monthsBetween = yearDiff * 12 + monthDiff;

        if (dayDiff < 0) {
            monthsBetween -= 1;
        }

        return Math.max(monthsBetween, 0);
    }
}

class Person {
    private String name;
    private String surname;
    private int age;
    private Date bDate;

    public Person() {
        this.name = "Unknown";
        this.surname = "Unknown";
        this.age = 0;
        this.bDate = new Date();
    }

    public Person(String name, String surname, int age, Date bDate) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.bDate = bDate;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Date getbDate() { return bDate; }
    public void setbDate(Date bDate) { this.bDate = bDate; }

    @Override
    public String toString() {
        return name + " " + surname + ", Age: " + age + ", Birth Date: " + bDate;
    }
}

class Account {
    private int accountNumber;
    private double balance;
    private Date dateCreated;
    private Person objPerson;

    public Account() {
        this.accountNumber = 0;
        this.balance = 0.0;
        this.dateCreated = new Date();
        this.objPerson = new Person();
    }

    public Account(int accountNumber, double balance, Date dateCreated, Person objPerson) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.dateCreated = dateCreated;
        this.objPerson = objPerson;
    }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public Date getDateCreated() { return dateCreated; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

    public Person getObjPerson() { return objPerson; }
    public void setObjPerson(Person objPerson) { this.objPerson = objPerson; }

    public void transferMoney(Account acc1, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            acc1.balance += amount;
            System.out.println("Transferred " + amount + " successfully.");
        } else {
            System.out.println("Insufficient balance to transfer.");
        }
    }

    public double getMonthlyInterest() {
        Date currentDate = new Date(5, 8, 2025);
        int monthsPassed = dateCreated.monthsBetween(currentDate);
        double annualRate = 0.045; 
        return balance * (annualRate / 12) * monthsPassed;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               "\nBalance: " + balance +
               "\nDate Created: " + dateCreated +
               "\nAccount Owner: " + objPerson;
    }
}

class SavingAccount extends Account {
    private static final double FEE = 20.0;

    public SavingAccount() {
        super();
    }

    public SavingAccount(int accountNumber, double balance, Date dateCreated, Person objPerson) {
        super(accountNumber, balance, dateCreated, objPerson);
    }

    @Override
    public void transferMoney(Account acc1, double amount) {
        double totalAmount = amount + FEE;
        if (getBalance() >= totalAmount) {
            setBalance(getBalance() - totalAmount);
            acc1.setBalance(acc1.getBalance() + amount);
            System.out.println("Transferred " + amount + " with fee " + FEE);
        } else {
            System.out.println("Insufficient balance to transfer including fee.");
        }
    }
}

class FixAccount extends Account {

    public FixAccount() {
        super();
    }

    public FixAccount(int accountNumber, double balance, Date dateCreated, Person objPerson) {
        super(accountNumber, balance, dateCreated, objPerson);
    }

    @Override
    public void transferMoney(Account acc1, double amount) {
        System.out.println("Transfer not allowed from FixAccount.");
    }
}

public class Application {
    public static void main(String[] args) {
        Date openDate1 = new Date(5, 8, 2025);
        Date openDate2 = new Date(9, 2, 2000);
        Person person1 = new Person("Joe", "Mama", 30, new Date(15, 6, 1995));
        Person person2 = new Person("Skibidi", "Toilet", 28, new Date(10, 3, 1997));

        SavingAccount acc1 = new SavingAccount(101, 1000.0, openDate1, person1);
        FixAccount acc2 = new FixAccount(102, 5000.0, openDate2, person2);

        System.out.println(">>:Before Transfer:<<");
        System.out.println(acc1 + "\n");
        System.out.println(acc2 + "\n___________________________________________\n");

        acc1.transferMoney(acc2, 200);

        System.out.println("\n>>:After Transfer:<<");
        System.out.println(acc1 + "\n");
        System.out.println(acc2 + "\n___________________________________________\n");

        acc2.transferMoney(acc1, 100); // Should not allow
    }
}
