
package banking;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class JdbcBankingService implements BankingService {

    // ==================== CREATE CUSTOMER ====================
    @Override
    public void addCustomer(Customer c) throws SQLException {
        String sql = "INSERT INTO customers(customer_id, name, address, contact) VALUES(?,?,?,?)";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, c.getCustomerID());
            p.setString(2, c.getName());
            p.setString(3, c.getAddress());
            p.setString(4, c.getContact());
            p.executeUpdate();
        }
    }

    // ==================== CREATE ACCOUNT ====================
    @Override
    public void addAccount(Account a) throws SQLException {
        String sql = "INSERT INTO accounts(account_id, customer_id, account_type, balance) VALUES(?,?,?,?)";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, a.getAccountID());
            p.setInt(2, a.getCustomerID());
            p.setString(3, a.getType());
            p.setBigDecimal(4, BigDecimal.valueOf(a.getBalance()));
            p.executeUpdate();
        }
    }

    // ==================== CREATE BENEFICIARY ====================
    @Override
    public void addBeneficiary(Beneficiary b) throws SQLException {
        String sql = "INSERT INTO beneficiaries(beneficiary_id, customer_id, name, account_number, bank_details) VALUES(?,?,?,?,?)";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, b.getBeneficiaryID());
            p.setInt(2, b.getCustomerID());
            p.setString(3, b.getName());
            p.setString(4, b.getAccountNumber());
            p.setString(5, b.getBankDetails());
            p.executeUpdate();
        }
    }

    // ==================== PROCESS TRANSACTION ====================
    @Override
    public void processTransaction(BankTransaction t) throws SQLException {
        String type = t.getType().trim().toLowerCase(Locale.ROOT);

        if (!type.equals("deposit") && !type.equals("withdrawal")) {
            throw new IllegalArgumentException(
                    "Use Deposit or Withdrawal.");
        }

        if (t.getAmount() <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero.");
        }

        try (Connection cn = DBConnection.getConnection()) {
            cn.setAutoCommit(false);

            try {
                BigDecimal balance;

                try (PreparedStatement p = cn.prepareStatement(
                        "SELECT balance FROM accounts WHERE account_id=? FOR UPDATE")) {

                    p.setInt(1, t.getAccountID());

                    try (ResultSet r = p.executeQuery()) {
                        if (!r.next()) {
                            throw new SQLException("Account not found.");
                        }
                        balance = r.getBigDecimal(1);
                    }
                }

                BigDecimal amount = BigDecimal.valueOf(t.getAmount());

                if (type.equals("withdrawal")
                        && balance.compareTo(amount) < 0) {
                    throw new SQLException("Insufficient balance.");
                }

                BigDecimal next = type.equals("deposit")
                        ? balance.add(amount)
                        : balance.subtract(amount);

                try (PreparedStatement p = cn.prepareStatement(
                        "UPDATE accounts SET balance=? WHERE account_id=?")) {

                    p.setBigDecimal(1, next);
                    p.setInt(2, t.getAccountID());
                    p.executeUpdate();
                }

                try (PreparedStatement p = cn.prepareStatement(
                        "INSERT INTO transactions(account_id, transaction_type, amount) VALUES(?,?,?)")) {

                    p.setInt(1, t.getAccountID());
                    p.setString(2, type.equals("deposit")
                            ? "Deposit" : "Withdrawal");
                    p.setBigDecimal(3, amount);
                    p.executeUpdate();
                }

                cn.commit();

            } catch (SQLException | RuntimeException e) {
                cn.rollback();
                throw e;
            }
        }
    }

    // ==================== READ CUSTOMER ====================
    @Override
    public Customer findCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE customer_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, id);

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    return new Customer(
                            r.getInt("customer_id"),
                            r.getString("name"),
                            r.getString("address"),
                            r.getString("contact"));
                }
                return null;
            }
        }
    }

    // ==================== READ ACCOUNT ====================
    @Override
    public Account findAccountById(int id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, id);

            try (ResultSet r = p.executeQuery()) {
                if (r.next()) {
                    return new Account(
                            r.getInt("account_id"),
                            r.getInt("customer_id"),
                            r.getString("account_type"),
                            r.getBigDecimal("balance").doubleValue());
                }
                return null;
            }
        }
    }

    // ==================== READ CUSTOMER ACCOUNTS ====================
    @Override
    public List<Account> getAccountsByCustomerId(int id)
            throws SQLException {

        List<Account> out = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE customer_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, id);

            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    out.add(new Account(
                            r.getInt("account_id"),
                            r.getInt("customer_id"),
                            r.getString("account_type"),
                            r.getBigDecimal("balance").doubleValue()));
                }
            }
        }

        return out;
    }

    // ==================== READ TRANSACTIONS ====================
    @Override
    public List<BankTransaction> getTransactionsByAccountId(int id)
            throws SQLException {

        List<BankTransaction> out = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id=? ORDER BY transaction_id";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, id);

            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    BankTransaction t = new BankTransaction(
                            r.getInt("account_id"),
                            r.getString("transaction_type"),
                            r.getBigDecimal("amount").doubleValue());

                    t.setTransactionID(r.getInt("transaction_id"));

                    Timestamp ts = r.getTimestamp("transaction_time");
                    if (ts != null) {
                        t.setTimestamp(ts.toLocalDateTime());
                    }

                    out.add(t);
                }
            }
        }

        return out;
    }

    // ==================== READ BENEFICIARIES ====================
    @Override
    public List<Beneficiary> getBeneficiariesByCustomerId(int id)
            throws SQLException {

        List<Beneficiary> out = new ArrayList<>();
        String sql = "SELECT * FROM beneficiaries WHERE customer_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, id);

            try (ResultSet r = p.executeQuery()) {
                while (r.next()) {
                    out.add(new Beneficiary(
                            r.getInt("beneficiary_id"),
                            r.getInt("customer_id"),
                            r.getString("name"),
                            r.getString("account_number"),
                            r.getString("bank_details")));
                }
            }
        }

        return out;
    }

    // ==================== UPDATE CUSTOMER ====================
    @Override
    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name=?, address=?, contact=? WHERE customer_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setString(1, customer.getName());
            p.setString(2, customer.getAddress());
            p.setString(3, customer.getContact());
            p.setInt(4, customer.getCustomerID());

            return p.executeUpdate() > 0;
        }
    }

    // ==================== DELETE CUSTOMER ====================
    @Override
    public boolean deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, customerId);
            return p.executeUpdate() > 0;
        }
    }

    // ==================== UPDATE ACCOUNT ====================
    @Override
    public boolean updateAccount(Account account) throws SQLException {
        String sql = "UPDATE accounts SET customer_id=?, account_type=? WHERE account_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, account.getCustomerID());
            p.setString(2, account.getType());
            p.setInt(3, account.getAccountID());

            return p.executeUpdate() > 0;
        }
    }

    // ==================== DELETE ACCOUNT ====================
    @Override
    public boolean deleteAccount(int accountId) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, accountId);
            return p.executeUpdate() > 0;
        }
    }

    // ==================== UPDATE BENEFICIARY ====================
    @Override
    public boolean updateBeneficiary(Beneficiary beneficiary)
            throws SQLException {

        String sql = "UPDATE beneficiaries SET name=?, account_number=?, bank_details=? WHERE beneficiary_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setString(1, beneficiary.getName());
            p.setString(2, beneficiary.getAccountNumber());
            p.setString(3, beneficiary.getBankDetails());
            p.setInt(4, beneficiary.getBeneficiaryID());

            return p.executeUpdate() > 0;
        }
    }

    // ==================== DELETE BENEFICIARY ====================
    @Override
    public boolean deleteBeneficiary(int beneficiaryId)
            throws SQLException {

        String sql = "DELETE FROM beneficiaries WHERE beneficiary_id=?";

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement p = cn.prepareStatement(sql)) {

            p.setInt(1, beneficiaryId);
            return p.executeUpdate() > 0;
        }
    }
}
