INSERT IGNORE INTO wards (ward_id, ward_name, total_beds, occupied_beds) VALUES
(1, 'ICU', 10, 0),
(2, 'Trauma', 15, 0),
(3, 'General', 30, 0),
(4, 'Pediatrics', 10, 0);

INSERT IGNORE INTO patients (patient_id, name, age, triage_level) VALUES
(101, 'Rahul Sharma', 45, 'CRITICAL'),
(102, 'Priya Patil', 28, 'URGENT'),
(103, 'Amit Kumar', 35, 'NORMAL'),
(104, 'Sneha Desai', 12, 'CRITICAL'),
(105, 'Rohan Mehta', 52, 'URGENT');
