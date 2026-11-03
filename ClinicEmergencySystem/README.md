# Clinic Emergency Bed & OPD Triage System

Java 21 + Spring Boot + Spring JDBC + MySQL console backend based on the Spring Mini Hackathon 2026 Problem Statement #2.

## Technology stack
- Java 21
- Maven
- Spring Boot 3.5.x / Spring Core / Spring Context
- Spring JDBC: JdbcTemplate and custom RowMapper classes
- MySQL 8.x and MySQL Connector/J
- DataSourceTransactionManager
- Git and GitHub

## Features
1. Patient registration with CRITICAL, URGENT or NORMAL triage.
2. Priority waiting queue (triage priority, then registration time).
3. Transaction-safe admission and bed allocation.
4. Discharge and safe bed release.
5. Ward occupancy and utilization report.

## Prerequisites
- JDK 21
- IntelliJ IDEA
- MySQL Server 8.x
- Maven (IntelliJ can use its bundled Maven)

## Database
Create the database in MySQL Workbench:

```sql
CREATE DATABASE clinic_emergency_db;
```

The application runs schema.sql and data.sql on startup.

## Credentials
By default the app connects to localhost:3306, database clinic_emergency_db, username root and an empty password. Change `src/main/resources/application.properties` or set:
- DB_URL
- DB_USERNAME
- DB_PASSWORD

Do not commit real database credentials to a public repository.

## Run
1. Open the extracted folder in IntelliJ.
2. Set Project SDK to JDK 21.
3. Wait for Maven import.
4. Start MySQL Server.
5. Run `com.clinic.emergency.ClinicApplication`.

Or run from a terminal:

```bash
mvn spring-boot:run
```

## Console menu
1 Register patient
2 View priority waiting queue
3 View wards and bed availability
4 Admit patient
5 Discharge patient
6 View admission history
7 Ward occupancy analytics
0 Exit

## Transaction safety
Admission locks the selected ward row using `SELECT ... FOR UPDATE`, checks capacity, increments the occupied-bed counter and inserts the admission in one transaction. A failure rolls the transaction back.

Discharge locks the active admission, updates its status and decrements the ward counter in one transaction.

## Notes
- This is an educational workflow demonstration, not a clinically validated triage system.
- Sample patient records are synthetic.
- `schema.sql` uses `CREATE TABLE IF NOT EXISTS`. Drop and recreate the database manually for a clean reset.
