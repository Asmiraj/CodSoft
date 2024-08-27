import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class BankAccount {
    private double balance;
    private String accountHolder;
    private List<String> transactionHistory;

    public BankAccount(double initialBalance, String accountHolder) {
        this.balance = initialBalance;
        this.accountHolder = accountHolder;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Initial deposit: $" + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: $" + amount);
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: $" + amount);
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid withdrawal amount.");
            return false;
        }
    }

    public double checkBalance() {
        return balance;
    }

    private void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class Atm {
    private BankAccount account;
    private static final DecimalFormat df = new DecimalFormat("#.00");
    private static final int TESTING_PIN = 1234; 
    
    public Atm(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0; 

        System.out.println("Testing PIN: " + TESTING_PIN);

        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter your PIN to access the ATM: ");
            int pin = scanner.nextInt();
            if (pin == TESTING_PIN) { 
                loggedIn = true;
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
        }

        boolean continueSession = true;
        while (continueSession) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: $");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: $");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.println("Current balance: $" + df.format(account.checkBalance()));
                        break;
                    case 4:
                        account.printTransactionHistory();
                        break;
                    case 5:
                        continueSession = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option from the menu.");
                        break;
                }

                if (choice != 5) {
                    System.out.print("Do you want to continue with another operation? (yes/no): ");
                    String response = scanner.next().toLowerCase();
                    if (response.equals("no")) {
                        continueSession = false;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.next(); 
            }
        }

        System.out.println("Thank you for choosing our ATM service today! We hope to see you again soon. Have a great day!");
        scanner.close();
    }
}

public class ATMInterface {
    public static void main(String[] args) {
     
        BankAccount account = new BankAccount(1000.00, "John Doe");
        Atm atm = new Atm(account);
         atm.displayMenu();
    }
}
