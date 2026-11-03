package com.clinic.emergency.mapper;

import com.clinic.emergency.model.Admission;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AdmissionRowMapper implements RowMapper<Admission> {
    @Override
    public Admission mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp admitted = rs.getTimestamp("admitted_at");
        Timestamp discharged = rs.getTimestamp("discharged_at");
        return new Admission(rs.getInt("admission_id"), rs.getInt("patient_id"),
                rs.getString("patient_name"), rs.getInt("ward_id"),
                rs.getString("ward_name"), rs.getString("attending_doctor"),
                rs.getString("status"),
                admitted == null ? null : admitted.toLocalDateTime(),
                discharged == null ? null : discharged.toLocalDateTime());
    }
}
