package com.example.clothing.DAO.Clothing_DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import com.example.clothing.ProductToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class ClothingDataAccessServiceOnProductName {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ProductToken productToken; 

    public ClothingDataAccessServiceOnProductName() {
        // Empty
    }

    public int executeUpdate(String sql) {
        return jdbcTemplate.update(sql);
    }
}