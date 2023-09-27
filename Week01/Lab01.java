import java.util.Date;
import java.util.Scanner;

public class Lab01 {
    public static void main(String[] args) {
        /*int[] l1 = makeList();
        int[] l2 = makeList();

        int[] merged = merge(l1, l2);
        printList(merged);*/

        /*Rectangle r1 = new Rectangle();
        r1.width = 4;
        r1.height = 40;

        Rectangle r2 = new Rectangle(3.5, 35.9);
        
        System.out.println("Rect-1 Width: " + r1.width + ", Height: " + r1.height + ", Area: " + r1.getArea() + ", Perimeter: " +r1.getPerimeter());
        System.out.println("Rect-2 Width: " + r2.width + ", Height: " + r2.height + ", Area: " + r2.getArea() + ", Perimeter: " +r2.getPerimeter());*/

        /*Account[] accounts = {new Account(1122, 20000), new Account(5316, 54000), new Account(1584, 83000)};
        Account.setAnnualInterestRate(4.5);
        for (Account account : accounts) {
            account.withdraw(30000);
            account.deposit(2000);
        }
        printGiven(accounts);*/


    }

    static int[] makeList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter list: ");
        int n = scanner.nextInt();
        int[] list = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = scanner.nextInt();
        }

        printList(list);
        return list;
    }

    static int[] merge(int[] l1, int[] l2) {
        int[] merged = new int[l1.length + l2.length];
        int m = 0, n = 0;

        while (m < l1.length && n < l2.length) {
            merged[m + n] = l1[m] < l2[n] ? l1[m++] : l2[n++];
        }

        while (m < l1.length) {
            merged[m + n] = l1[m++];
        }

        while (n < l2.length) {
            merged[m + n] = l2[n++];
        }

        return merged;
    }

    static void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

    static void printGiven(Account... accs) {
        for (int i = 0; i < accs.length; i++) {
            accs[i].accountInformation();
        }
    }
}

class Rectangle {
    double width = 1;
    double height = 1;

    Rectangle() {}

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double getArea() {
        return width * height;
    }

    double getPerimeter() {
        return 2 * (width + height);
    }
}

class Account {
    private int id = 0;
    private double balance = 0;
    private static double annualInterestRate = 0;
    private Date dateCreated;

    private static int accountCount = 0;
    private boolean edited = false;


    Account() {
        this.dateCreated = new Date();
        accountCount++;
    }

    Account(int id, double balance) {
        this(); // no need to rewrite, can use no-arg constructor. Has to be first line

        this.id = id;
        this.balance = balance;
    }

    static int getAccountCount() {
        return accountCount;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;

        edited = true;
        // or some logging operation
    }

    boolean isEdited() {
        return edited;
    }

    double getBalance() {
        return balance;
    }

    /*
    We can make setBalance private too. Only way of updating balance is through withdraw and deposit methods.
    within mentioned methods, we can use this method to do some trivial stuff like logging and abstract complexity
    */
    private void setBalance(double balance) {
        this.balance = balance;
    }

    double getAnnualInterestRate() {
        return Account.annualInterestRate;
    }

    static void setAnnualInterestRate(double annualInterestRate) {
        Account.annualInterestRate = annualInterestRate / 100;
    }

    /*Date getDateCreated() {
        return this.dateCreated;
    }*/

    //We can either use above method or below one. If we use the one that returns String
    //dateCreated data field becomes immutable, because Strings are immutable
    String getDateCreated() {
        return this.dateCreated.toString();
    }

    double getMonthlyInterestRate() {
        return Account.annualInterestRate / 12;
    }

    double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    void withdraw(double amount) {
        //balance -= amount;

        //We can make error control
        if (amount < 0) {
            System.out.println("ERROR: Cannot withdraw a negative amount");
        }
        else if (amount > balance) {
            System.out.println("ERROR: Cannot withdraw more than balance");
        }
        else {
            this.setBalance(balance - amount);
        }
    }

    void deposit(double amount) {
        //balance += amount;

        if (amount < 0) {
            System.out.println("ERROR: Cannot deposit a negative amount");
        }
        else {
            balance += amount;
        }
    }

    void accountInformation() {
        System.out.println("AccountId: " + this.id);
        System.out.println("Balance: " + this.getBalance());
        System.out.println("Monthly Interest: " + this.getMonthlyInterest());
        System.out.println("Date Created: " + this.getDateCreated());
    }
}


