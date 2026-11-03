CREATE TABLE IF NOT EXISTS wards (
    ward_id INT AUTO_INCREMENT PRIMARY KEY,
    ward_name VARCHAR(100) NOT NULL UNIQUE,
    total_beds INT NOT NULL,
    occupied_beds INT NOT NULL DEFAULT 0,
    CONSTRAINT chk_total_beds CHECK (total_beds >= 0),
    CONSTRAINT chk_occupied_beds CHECK (occupied_beds >= 0 AND occupied_beds <= total_beds)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    triage_level VARCHAR(20) NOT NULL,
    registered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_patient_age CHECK (age >= 0),
    CONSTRAINT chk_triage_level CHECK (triage_level IN ('CRITICAL', 'URGENT', 'NORMAL')),
    INDEX idx_patient_triage (triage_level, registered_at)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS admissions (
    admission_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    ward_id INT NOT NULL,
    attending_doctor VARCHAR(100) NOT NULL,
    status VARCHAR(30) NOT NULL,
    admitted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    discharged_at TIMESTAMP NULL,
    CONSTRAINT fk_admission_patient FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    CONSTRAINT fk_admission_ward FOREIGN KEY (ward_id) REFERENCES wards(ward_id),
    CONSTRAINT chk_admission_status CHECK (status IN ('ACTIVE', 'DISCHARGED', 'TRANSFERRED')),
    INDEX idx_admission_status (status),
    INDEX idx_admission_patient (patient_id),
    INDEX idx_admission_ward_status (ward_id, status)
) ENGINE=InnoDB;
