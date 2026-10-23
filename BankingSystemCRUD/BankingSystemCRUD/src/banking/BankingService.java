package banking;

import java.util.Collection;
import java.util.List;

public interface BankingService {
    // CREATE
    void addCustomer(Customer customer);
    void addAccount(Account account);
    void addBeneficiary(Beneficiary beneficiary);
    void addTransaction(int transactionID, int accountID, String type, double amount);

    // READ
    Customer findCustomerById(int id);
    Account findAccountById(int id);
    Transaction findTransactionById(int id);
    Beneficiary findBeneficiaryById(int id);
    List<Account> getAccountsByCustomerId(int customerId);
    List<Transaction> getTransactionsByAccountId(int accountId);
    List<Beneficiary> getBeneficiariesByCustomerId(int customerId);
    Collection<Customer> getAllCustomers();
    Collection<Account> getAllAccounts();
    Collection<Transaction> getAllTransactions();
    Collection<Beneficiary> getAllBeneficiaries();

    // UPDATE
    boolean updateCustomer(int id, String name, String address, String contact);
    boolean updateAccountType(int id, String type);
    boolean updateBeneficiary(int id, String name, String accountNumber, String bankDetails);

    // DELETE
    boolean deleteCustomer(int id);
    boolean deleteAccount(int id);
    boolean deleteTransaction(int id);
    boolean deleteBeneficiary(int id);
}
