BANKING SYSTEM CRUD CASE STUDY (CORE JAVA)

Requirements: JDK 17 or later (uses switch arrow syntax and var).
No external libraries are required.

INTELLIJ IDEA STEPS
1. Extract BankingSystemCRUD.zip.
2. IntelliJ: File > Open > select the extracted BankingSystemCRUD folder.
3. Configure Project SDK to JDK 17 or later.
4. Open src/banking/BankingSystemApp.java.
5. Click the green Run icon beside main().

If creating the project manually, create a Java project and a package named banking.
Create these seven Java files in that package and paste the matching source files:
Customer.java, Account.java, Transaction.java, Beneficiary.java,
BankingService.java, BankingServiceImpl.java, BankingSystemApp.java.

MENU QUICK TEST
1. Add customer: ID 1, Aniket, Pune, 9876543210
2. Add account: ID 101, customer ID 1, Saving, opening balance 70000
4. Deposit/withdraw: transaction ID 1, account ID 101, Deposit, 10000
6. List accounts for customer 1 -> balance should be 80000
7. List transactions for account 101
9. Update customer 1
11. Update a beneficiary after adding one
16. Show all records
0. Exit

NOTES
- In-memory data resets when the program exits.
- This is an educational console project, not real banking software.
- Deleting a transaction removes only the history record; it does not reverse the account balance.
- An account with transaction history cannot be deleted, and a customer with accounts/beneficiaries cannot be deleted until those dependencies are removed.
