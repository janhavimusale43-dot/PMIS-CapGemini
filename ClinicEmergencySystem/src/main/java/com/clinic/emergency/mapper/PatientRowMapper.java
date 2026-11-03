package com.clinic.emergency.mapper;

import com.clinic.emergency.model.Patient;
import com.clinic.emergency.model.TriageLevel;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient> {
    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Patient(rs.getInt("patient_id"), rs.getString("name"),
                rs.getInt("age"), TriageLevel.valueOf(rs.getString("triage_level")),
                rs.getTimestamp("registered_at").toLocalDateTime());
    }
}
