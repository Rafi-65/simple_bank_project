import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int accountCounter = 1000; // Starting account number

    public static void main(String[] args) {
        System.out.println("=== Welcome to Simple Banking System ===\n");
        
        boolean running = true;
        
        while (running) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Create New Account");
            System.out.println("2. Access Existing Account");
            System.out.println("3. View All Accounts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            
            int choice = getIntInput(1, 4);
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    accessAccount();
                    break;
                case 3:
                    viewAllAccounts();
                    break;
                case 4:
                    running = false;
                    System.out.println("\nThank you for using Simple Banking System. Goodbye!");
                    break;
            }
        }
        
        scanner.close();
    }
    
    private static void createAccount() {
        System.out.println("\n=== Create New Account ===");
        System.out.print("Enter account holder name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        
        System.out.print("Enter initial deposit amount: $");
        double initialDeposit = getPositiveDoubleInput();
        
        String accountNumber = "ACC" + accountCounter++;
        Account newAccount = new Account(accountNumber, name, initialDeposit);
        accounts.add(newAccount);
        
        System.out.println("\nAccount created successfully!");
        System.out.println("Your account number is: " + accountNumber);
        newAccount.displayAccountInfo();
    }
    
    private static void accessAccount() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts exist yet. Please create an account first.");
            return;
        }
        
        System.out.print("\nEnter account number: ");
        String accNumber = scanner.next();
        
        Account account = findAccount(accNumber);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        
        boolean backToMain = false;
        
        while (!backToMain) {
            System.out.println("\n=== Account Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Account Info");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice (1-5): ");
            
            int choice = getIntInput(1, 5);
            
            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = getPositiveDoubleInput();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = getPositiveDoubleInput();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    account.displayAccountInfo();
                    break;
                case 5:
                    backToMain = true;
                    break;
            }
        }
    }
    
    private static void viewAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts exist yet.");
            return;
        }
        
        System.out.println("\n=== All Accounts ===");
        for (Account acc : accounts) {
            acc.displayAccountInfo();
        }
    }
    
    private static Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }
        return null;
    }
    
    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next(); // Consume invalid input
            }
        }
    }
    
    private static double getPositiveDoubleInput() {
        while (true) {
            try {
                double input = scanner.nextDouble();
                if (input > 0) {
                    return input;
                }
                System.out.print("Amount must be positive. Please try again: $");
            } catch (Exception e) {
                System.out.print("Invalid input. Please enter a valid amount: $");
                scanner.next(); // Consume invalid input
            }
        }
    }
}