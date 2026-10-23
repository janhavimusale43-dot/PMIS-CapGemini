# Banking System JDBC project

1. Open this folder in IntelliJ as a Maven project.
2. In MySQL Workbench, connect to your local MySQL Server and run `database.sql`.
3. In `src/main/java/banking/DBConnection.java`, replace `YOUR_MYSQL_PASSWORD` with your MySQL password. Update USER if your username is not root.
4. Reload Maven and wait for `mysql-connector-j` to download.
5. Run `ConnectionTest.java`. Then run `BankingSystemApp.java`.
6. Add a customer before adding that customer's account; add an account before a deposit/withdrawal.

This is an educational example, not production banking software. It does not implement authentication or real inter-bank transfers. Never upload your database password to GitHub.
