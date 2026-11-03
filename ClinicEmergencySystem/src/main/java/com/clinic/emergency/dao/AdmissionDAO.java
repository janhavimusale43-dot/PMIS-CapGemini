package com.clinic.emergency.dao;

import com.clinic.emergency.mapper.AdmissionRowMapper;
import com.clinic.emergency.model.Admission;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class AdmissionDAO {
    private final JdbcTemplate jdbcTemplate;
    private final AdmissionRowMapper rowMapper = new AdmissionRowMapper();

    public AdmissionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(int patientId, int wardId, String doctor) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO admissions (patient_id, ward_id, attending_doctor, status) " +
                    "VALUES (?, ?, ?, 'ACTIVE')", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, patientId);
            ps.setInt(2, wardId);
            ps.setString(3, doctor);
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        if (key == null) throw new IllegalStateException("Admission ID was not generated.");
        return key.intValue();
    }

    public boolean hasActiveAdmission(int patientId) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM admissions WHERE patient_id = ? AND status = 'ACTIVE'",
                Integer.class, patientId);
        return count != null && count > 0;
    }

    public Optional<Admission> findActiveByIdForUpdate(int admissionId) {
        String sql = """
                SELECT a.admission_id, a.patient_id, p.name AS patient_name,
                       a.ward_id, w.ward_name, a.attending_doctor,
                       a.status, a.admitted_at, a.discharged_at
                FROM admissions a
                JOIN patients p ON p.patient_id = a.patient_id
                JOIN wards w ON w.ward_id = a.ward_id
                WHERE a.admission_id = ? AND a.status = 'ACTIVE'
                FOR UPDATE
                """;
        List<Admission> rows = jdbcTemplate.query(sql, rowMapper, admissionId);
        return rows.stream().findFirst();
    }

    public int discharge(int admissionId) {
        return jdbcTemplate.update(
                "UPDATE admissions SET status = 'DISCHARGED', discharged_at = CURRENT_TIMESTAMP " +
                "WHERE admission_id = ? AND status = 'ACTIVE'", admissionId);
    }

    public List<Admission> findAll() {
        String sql = """
                SELECT a.admission_id, a.patient_id, p.name AS patient_name,
                       a.ward_id, w.ward_name, a.attending_doctor,
                       a.status, a.admitted_at, a.discharged_at
                FROM admissions a
                JOIN patients p ON p.patient_id = a.patient_id
                JOIN wards w ON w.ward_id = a.ward_id
                ORDER BY a.admission_id DESC
                """;
        return jdbcTemplate.query(sql, rowMapper);
    }
}
