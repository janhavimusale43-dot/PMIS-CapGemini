package com.clinic.emergency.dao;

import com.clinic.emergency.mapper.PatientRowMapper;
import com.clinic.emergency.model.Patient;
import com.clinic.emergency.model.TriageLevel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PatientRowMapper rowMapper = new PatientRowMapper();

    public PatientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int create(String name, int age, TriageLevel triageLevel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO patients (name, age, triage_level) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, triageLevel.name());
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        if (key == null) throw new IllegalStateException("Patient ID was not generated.");
        return key.intValue();
    }

    public Optional<Patient> findById(int patientId) {
        List<Patient> rows = jdbcTemplate.query(
                "SELECT * FROM patients WHERE patient_id = ?", rowMapper, patientId);
        return rows.stream().findFirst();
    }

    public List<Patient> findWaitingPatients() {
        String sql = """
                SELECT p.* FROM patients p
                WHERE NOT EXISTS (
                    SELECT 1 FROM admissions a
                    WHERE a.patient_id = p.patient_id AND a.status = 'ACTIVE'
                )
                ORDER BY CASE p.triage_level
                    WHEN 'CRITICAL' THEN 1
                    WHEN 'URGENT' THEN 2
                    WHEN 'NORMAL' THEN 3
                END, p.registered_at ASC
                """;
        return jdbcTemplate.query(sql, rowMapper);
    }
}
