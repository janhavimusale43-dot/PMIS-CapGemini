package com.clinic.emergency.mapper;

import com.clinic.emergency.model.Ward;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WardRowMapper implements RowMapper<Ward> {
    @Override
    public Ward mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Ward(rs.getInt("ward_id"), rs.getString("ward_name"),
                rs.getInt("total_beds"), rs.getInt("occupied_beds"));
    }
}
