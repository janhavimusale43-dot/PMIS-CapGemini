
package banking;

import java.sql.SQLException;
import java.util.List;

public interface BankingService {

    // CREATE
    void addCustomer(Customer c) throws SQLException;
    void addAccount(Account a) throws SQLException;
    void addBeneficiary(Beneficiary b) throws SQLException;
    void processTransaction(BankTransaction t) throws SQLException;

    // READ
    Customer findCustomerById(int id) throws SQLException;
    Account findAccountById(int id) throws SQLException;
    List<Account> getAccountsByCustomerId(int id) throws SQLException;
    List<BankTransaction> getTransactionsByAccountId(int id) throws SQLException;
    List<Beneficiary> getBeneficiariesByCustomerId(int id) throws SQLException;

    // UPDATE
    boolean updateCustomer(Customer customer) throws SQLException;
    boolean updateAccount(Account account) throws SQLException;
    boolean updateBeneficiary(Beneficiary beneficiary) throws SQLException;

    // DELETE
    boolean deleteCustomer(int customerId) throws SQLException;
    boolean deleteAccount(int accountId) throws SQLException;
    boolean deleteBeneficiary(int beneficiaryId) throws SQLException;
}
