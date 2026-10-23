CREATE DATABASE IF NOT EXISTS banking_db;
USE banking_db;
CREATE TABLE IF NOT EXISTS customers (
 customer_id INT PRIMARY KEY, name VARCHAR(100) NOT NULL,
 address VARCHAR(200), contact VARCHAR(30));
CREATE TABLE IF NOT EXISTS accounts (
 account_id INT PRIMARY KEY, customer_id INT NOT NULL,
 account_type VARCHAR(20) NOT NULL, balance DECIMAL(15,2) NOT NULL DEFAULT 0,
 FOREIGN KEY (customer_id) REFERENCES customers(customer_id));
CREATE TABLE IF NOT EXISTS beneficiaries (
 beneficiary_id INT PRIMARY KEY, customer_id INT NOT NULL,
 name VARCHAR(100) NOT NULL, account_number VARCHAR(50) NOT NULL, bank_details VARCHAR(100),
 FOREIGN KEY (customer_id) REFERENCES customers(customer_id));
CREATE TABLE IF NOT EXISTS transactions (
 transaction_id INT AUTO_INCREMENT PRIMARY KEY, account_id INT NOT NULL,
 transaction_type VARCHAR(20) NOT NULL, amount DECIMAL(15,2) NOT NULL,
 transaction_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (account_id) REFERENCES accounts(account_id));
