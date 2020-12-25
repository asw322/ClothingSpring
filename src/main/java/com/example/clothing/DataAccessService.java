/**
 * Basic API backend class
 * Has basic getter and setter functions to the Postgres database
 */

package com.example.clothing;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

@Component
public class DataAccessService {

    // @Autowired
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    public DataAccessService() {
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
}