package banking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingServiceImpl implements BankingService {
    private final Map<Integer, Customer> customers = new HashMap<>();
    private final Map<Integer, Account> accounts = new HashMap<>();
    private final Map<Integer, Transaction> transactions = new HashMap<>();
    private final Map<Integer, Beneficiary> beneficiaries = new HashMap<>();

    @Override public void addCustomer(Customer c) {
        if (customers.containsKey(c.getCustomerID())) throw new IllegalArgumentException("Customer ID already exists.");
        customers.put(c.getCustomerID(), c);
    }
    @Override public void addAccount(Account a) {
        if (!customers.containsKey(a.getCustomerID())) throw new IllegalArgumentException("Customer does not exist.");
        if (accounts.containsKey(a.getAccountID())) throw new IllegalArgumentException("Account ID already exists.");
        if (a.getBalance() < 0) throw new IllegalArgumentException("Opening balance cannot be negative.");
        accounts.put(a.getAccountID(), a);
    }
    @Override public void addBeneficiary(Beneficiary b) {
        if (!customers.containsKey(b.getCustomerID())) throw new IllegalArgumentException("Customer does not exist.");
        if (beneficiaries.containsKey(b.getBeneficiaryID())) throw new IllegalArgumentException("Beneficiary ID already exists.");
        beneficiaries.put(b.getBeneficiaryID(), b);
    }
    @Override public void addTransaction(int transactionID, int accountID, String type, double amount) {
        Account a = accounts.get(accountID);
        if (a == null) throw new IllegalArgumentException("Account does not exist.");
        if (transactions.containsKey(transactionID)) throw new IllegalArgumentException("Transaction ID already exists.");
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero.");
        String normalized = type.trim().toLowerCase();
        if (normalized.equals("deposit")) a.setBalance(a.getBalance() + amount);
        else if (normalized.equals("withdrawal") || normalized.equals("withdraw")) {
            if (a.getBalance() < amount) throw new IllegalArgumentException("Insufficient balance.");
            a.setBalance(a.getBalance() - amount);
            normalized = "withdrawal";
        } else throw new IllegalArgumentException("Type must be Deposit or Withdrawal.");
        transactions.put(transactionID, new Transaction(transactionID, accountID, normalized, amount));
    }

    @Override public Customer findCustomerById(int id) { return customers.get(id); }
    @Override public Account findAccountById(int id) { return accounts.get(id); }
    @Override public Transaction findTransactionById(int id) { return transactions.get(id); }
    @Override public Beneficiary findBeneficiaryById(int id) { return beneficiaries.get(id); }
    @Override public List<Account> getAccountsByCustomerId(int id) {
        List<Account> result = new ArrayList<>();
        for (Account a : accounts.values()) if (a.getCustomerID() == id) result.add(a);
        return result;
    }
    @Override public List<Transaction> getTransactionsByAccountId(int id) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction t : transactions.values()) if (t.getAccountID() == id) result.add(t);
        return result;
    }
    @Override public List<Beneficiary> getBeneficiariesByCustomerId(int id) {
        List<Beneficiary> result = new ArrayList<>();
        for (Beneficiary b : beneficiaries.values()) if (b.getCustomerID() == id) result.add(b);
        return result;
    }
    @Override public Collection<Customer> getAllCustomers() { return customers.values(); }
    @Override public Collection<Account> getAllAccounts() { return accounts.values(); }
    @Override public Collection<Transaction> getAllTransactions() { return transactions.values(); }
    @Override public Collection<Beneficiary> getAllBeneficiaries() { return beneficiaries.values(); }

    @Override public boolean updateCustomer(int id, String name, String address, String contact) {
        Customer c = customers.get(id); if (c == null) return false;
        c.setName(name); c.setAddress(address); c.setContact(contact); return true;
    }
    @Override public boolean updateAccountType(int id, String type) {
        Account a = accounts.get(id); if (a == null) return false;
        if (!type.equalsIgnoreCase("Saving") && !type.equalsIgnoreCase("Current")) throw new IllegalArgumentException("Type must be Saving or Current.");
        a.setType(type); return true;
    }
    @Override public boolean updateBeneficiary(int id, String name, String accountNumber, String bankDetails) {
        Beneficiary b = beneficiaries.get(id); if (b == null) return false;
        b.setName(name); b.setAccountNumber(accountNumber); b.setBankDetails(bankDetails); return true;
    }
    @Override public boolean deleteCustomer(int id) {
        if (!customers.containsKey(id)) return false;
        if (!getAccountsByCustomerId(id).isEmpty() || !getBeneficiariesByCustomerId(id).isEmpty())
            throw new IllegalArgumentException("Delete this customer's accounts and beneficiaries first.");
        customers.remove(id); return true;
    }
    @Override public boolean deleteAccount(int id) {
        if (!accounts.containsKey(id)) return false;
        if (!getTransactionsByAccountId(id).isEmpty()) throw new IllegalArgumentException("Cannot delete an account with transaction history.");
        accounts.remove(id); return true;
    }
    @Override public boolean deleteTransaction(int id) {
        // In this learning project, deleting a transaction only removes its history record; it does not reverse the balance.
        return transactions.remove(id) != null;
    }
    @Override public boolean deleteBeneficiary(int id) { return beneficiaries.remove(id) != null; }
}
