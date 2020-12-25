package com.example.clothing.DAO;

/**
 * Basic API backend class or also called the Data Access Object (DAO)
 * Has basic getter and setter functions to the Postgres database
 */

/**
 * TODO: Still need GET, UPDATE, DELETE, CREATE
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import com.example.clothing.UserToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

@Component
public class UserDataAccessService {

    // @Autowired
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    public UserDataAccessService() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public List<UserToken> getAllPeople() {
        System.out.println("Grabbing all users!");
        final String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, new RowMapper<UserToken>() {

            @Override
            public UserToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                System.out.println("Setting up new user!");
                UserToken temp = new UserToken();
                temp.setLogin(rs.getString("name"), rs.getString("hashedpassword"), rs.getString("id"));
                return temp;
            }
        });
    }

    // Warning: not general use of executing user token query 
    public List<UserToken> executeUserTokenQuery(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<UserToken>() {

            @Override
            public UserToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                UserToken temp = new UserToken();
                temp.setLogin(rs.getString("name"), rs.getString("hashedpassword"), rs.getString("id"));
                return temp;
            }
        });
    }
}