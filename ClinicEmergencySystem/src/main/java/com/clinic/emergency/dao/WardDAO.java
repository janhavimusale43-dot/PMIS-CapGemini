package com.clinic.emergency.dao;

import com.clinic.emergency.mapper.WardRowMapper;
import com.clinic.emergency.model.Ward;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class WardDAO {
    private final JdbcTemplate jdbcTemplate;
    private final WardRowMapper rowMapper = new WardRowMapper();

    public WardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ward> findAll() {
        return jdbcTemplate.query("SELECT * FROM wards ORDER BY ward_id", rowMapper);
    }

    public Optional<Ward> findByIdForUpdate(int wardId) {
        List<Ward> rows = jdbcTemplate.query(
                "SELECT * FROM wards WHERE ward_id = ? FOR UPDATE", rowMapper, wardId);
        return rows.stream().findFirst();
    }

    public int incrementOccupiedBeds(int wardId) {
        return jdbcTemplate.update(
                "UPDATE wards SET occupied_beds = occupied_beds + 1 " +
                "WHERE ward_id = ? AND occupied_beds < total_beds", wardId);
    }

    public int decrementOccupiedBeds(int wardId) {
        return jdbcTemplate.update(
                "UPDATE wards SET occupied_beds = occupied_beds - 1 " +
                "WHERE ward_id = ? AND occupied_beds > 0", wardId);
    }
}
