import java.util.*;

interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

abstract class BankAccount implements Loanable {
    private String accountNumber;
    private String holderName;
    protected double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid withdraw attempt.");
        }
    }

    public abstract double calculateInterest();
}

class SavingsAccount extends BankAccount {

    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.04;
    }

    @Override
    public void applyForLoan(double amount) {
        System.out.println("Savings Account Loan Application for $" + amount);
    }

    @Override
    public boolean calculateLoanEligibility() {
        return balance >= 1000;
    }
}

class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return 0.0; 
    }

    @Override
    public void applyForLoan(double amount) {
        System.out.println("Current Account Loan Application for $" + amount);
    }

    @Override
    public boolean calculateLoanEligibility() {
        return balance >= 5000;
    }
}

public class BankingSystem {
    public static void main(String[] args) {
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV1001", "Alice", 1200.0));
        accounts.add(new CurrentAccount("CUR2002", "Bob", 7000.0));

        for (BankAccount account : accounts) {
            System.out.println("Account: " + account.getAccountNumber());
            System.out.println("Holder: " + account.getHolderName());
            System.out.println("Balance: $" + account.getBalance());
            System.out.println("Interest: $" + account.calculateInterest());
            account.deposit(300);
            account.withdraw(150);
            account.applyForLoan(2000);
            System.out.println("Loan Eligible: " + account.calculateLoanEligibility());
            System.out.println("-------------------------------");
        }
    }
}
