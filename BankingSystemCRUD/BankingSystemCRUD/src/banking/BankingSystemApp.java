package banking;

import java.util.Scanner;

public class BankingSystemApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final BankingService service = new BankingServiceImpl();

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1 -> addCustomer();
                    case 2 -> addAccount();
                    case 3 -> addBeneficiary();
                    case 4 -> addTransaction();
                    case 5 -> findCustomer();
                    case 6 -> listAccounts();
                    case 7 -> listTransactions();
                    case 8 -> listBeneficiaries();
                    case 9 -> updateCustomer();
                    case 10 -> updateAccount();
                    case 11 -> updateBeneficiary();
                    case 12 -> deleteCustomer();
                    case 13 -> deleteAccount();
                    case 14 -> deleteTransaction();
                    case 15 -> deleteBeneficiary();
                    case 16 -> showAllRecords();
                    case 0 -> System.out.println("Thank you for using the Banking System!");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Operation failed: " + ex.getMessage());
            }
        } while (choice != 0);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n========== BANKING SYSTEM ==========");
        System.out.println("1. Add Customer             2. Add Account");
        System.out.println("3. Add Beneficiary          4. Deposit/Withdraw");
        System.out.println("5. Find Customer            6. Accounts by Customer");
        System.out.println("7. Transactions by Account  8. Beneficiaries by Customer");
        System.out.println("9. Update Customer          10. Update Account Type");
        System.out.println("11. Update Beneficiary      12. Delete Customer");
        System.out.println("13. Delete Account          14. Delete Transaction Record");
        System.out.println("15. Delete Beneficiary      16. Show All Records");
        System.out.println("0. Exit");
    }
    private static int readInt(String prompt) { System.out.print(prompt); while (!sc.hasNextInt()) { System.out.print("Enter a valid whole number: "); sc.next(); } int v = sc.nextInt(); sc.nextLine(); return v; }
    private static double readDouble(String prompt) { System.out.print(prompt); while (!sc.hasNextDouble()) { System.out.print("Enter a valid amount: "); sc.next(); } double v = sc.nextDouble(); sc.nextLine(); return v; }
    private static String readText(String prompt) { System.out.print(prompt); return sc.nextLine().trim(); }
    private static void addCustomer() {
        int id = readInt("Customer ID: "); String name = readText("Name: ");
        String address = readText("Address: "); String contact = readText("Contact: ");
        service.addCustomer(new Customer(id, name, address, contact)); System.out.println("Customer added successfully.");
    }
    private static void addAccount() {
        int id = readInt("Account ID: "); int cid = readInt("Customer ID: ");
        String type = readText("Account type (Saving/Current): "); double balance = readDouble("Opening balance: ");
        if (!type.equalsIgnoreCase("Saving") && !type.equalsIgnoreCase("Current")) throw new IllegalArgumentException("Type must be Saving or Current.");
        service.addAccount(new Account(id, cid, type, balance)); System.out.println("Account created successfully.");
    }
    private static void addBeneficiary() {
        int id = readInt("Beneficiary ID: "); int cid = readInt("Customer ID: ");
        String name = readText("Beneficiary name: "); String number = readText("Beneficiary account number: ");
        String bank = readText("Bank details: ");
        service.addBeneficiary(new Beneficiary(id, cid, name, number, bank)); System.out.println("Beneficiary added successfully.");
    }
    private static void addTransaction() {
        int tid = readInt("Transaction ID: "); int aid = readInt("Account ID: ");
        String type = readText("Type (Deposit/Withdrawal): "); double amount = readDouble("Amount: ");
        service.addTransaction(tid, aid, type, amount); System.out.println("Transaction recorded. Updated account: " + service.findAccountById(aid));
    }
    private static void findCustomer() {
        int id = readInt("Customer ID: "); Customer c = service.findCustomerById(id);
        System.out.println(c == null ? "Customer not found." : c);
    }
    private static void listAccounts() {
        int id = readInt("Customer ID: "); var list = service.getAccountsByCustomerId(id);
        if (list.isEmpty()) System.out.println("No accounts found for this customer."); else list.forEach(System.out::println);
    }
    private static void listTransactions() {
        int id = readInt("Account ID: "); var list = service.getTransactionsByAccountId(id);
        if (list.isEmpty()) System.out.println("No transactions found for this account."); else list.forEach(System.out::println);
    }
    private static void listBeneficiaries() {
        int id = readInt("Customer ID: "); var list = service.getBeneficiariesByCustomerId(id);
        if (list.isEmpty()) System.out.println("No beneficiaries found for this customer."); else list.forEach(System.out::println);
    }
    private static void updateCustomer() {
        int id = readInt("Customer ID to update: "); String name = readText("New name: ");
        String address = readText("New address: "); String contact = readText("New contact: ");
        System.out.println(service.updateCustomer(id, name, address, contact) ? "Customer updated." : "Customer not found.");
    }
    private static void updateAccount() {
        int id = readInt("Account ID to update: "); String type = readText("New type (Saving/Current): ");
        System.out.println(service.updateAccountType(id, type) ? "Account updated." : "Account not found.");
    }
    private static void updateBeneficiary() {
        int id = readInt("Beneficiary ID to update: "); String name = readText("New name: ");
        String number = readText("New account number: "); String bank = readText("New bank details: ");
        System.out.println(service.updateBeneficiary(id, name, number, bank) ? "Beneficiary updated." : "Beneficiary not found.");
    }
    private static void deleteCustomer() { int id = readInt("Customer ID to delete: "); System.out.println(service.deleteCustomer(id) ? "Customer deleted." : "Customer not found."); }
    private static void deleteAccount() { int id = readInt("Account ID to delete: "); System.out.println(service.deleteAccount(id) ? "Account deleted." : "Account not found."); }
    private static void deleteTransaction() { int id = readInt("Transaction ID to delete: "); System.out.println(service.deleteTransaction(id) ? "Transaction record deleted (balance unchanged)." : "Transaction not found."); }
    private static void deleteBeneficiary() { int id = readInt("Beneficiary ID to delete: "); System.out.println(service.deleteBeneficiary(id) ? "Beneficiary deleted." : "Beneficiary not found."); }
    private static void showAllRecords() {
        System.out.println("\n--- CUSTOMERS ---"); if (service.getAllCustomers().isEmpty()) System.out.println("None"); else service.getAllCustomers().forEach(System.out::println);
        System.out.println("\n--- ACCOUNTS ---"); if (service.getAllAccounts().isEmpty()) System.out.println("None"); else service.getAllAccounts().forEach(System.out::println);
        System.out.println("\n--- TRANSACTIONS ---"); if (service.getAllTransactions().isEmpty()) System.out.println("None"); else service.getAllTransactions().forEach(System.out::println);
        System.out.println("\n--- BENEFICIARIES ---"); if (service.getAllBeneficiaries().isEmpty()) System.out.println("None"); else service.getAllBeneficiaries().forEach(System.out::println);
    }
}
