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

import com.example.clothing.ProductToken;
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

    private ProductToken productToken;


    public UserDataAccessService() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    /**
     * Returns a list of all users
     * @return
     */
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


    /**
     * Gets the user data for login 
     * @param sql
     * @return
     */
    public List<UserToken> getUserData(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<UserToken>() {

            @Override
            public UserToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                UserToken temp = new UserToken();
                temp.setLogin(rs.getString("name"), rs.getString("hashedpassword"), rs.getString("id"));
                return temp;
            }
        });
    }

    /**
     * Get wear count on some specific user owned product
     * Returns on [null, -1] represent error cases 
     * @param sql
     * @return
     */
    public List<Integer> getWearCount(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNumber) throws SQLException {
                try {
                    return Integer.parseInt(rs.getString("wear_count"));
                }
                catch(NumberFormatException e) {
                    System.out.println("Wear count retrieval error");
                    return -1;
                }
            }
        });
    }


    /**
     * Calls ClothingDataAccessService getProductDetail 
     * on products that are owned by the user (under the same ID)
     * @param sql
     * @return
     */
    public List<ProductToken> getUserOwnedProductDetail(String sql) {
        // CALL GETPRODUCTDETAIL HERE
        return null;
    }


    /**
     * Generates a new ID by taking the highest ID + 1 
     */
    public int generateNewID() {
        final String sql = "SELECT MAX(id) AS largestId FROM person";
        List<Integer> res= jdbcTemplate.query(sql, new RowMapper<Integer>() {
            
            @Override
            public Integer mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return Integer.parseInt(rs.getString("largestId"));
            }
        });

        return (res.get(0) + 1);
    }



    /**
     * Inserting a user user into the person table
     */
    public void insertNewUser(UserToken user) {
        final String sql = "INSERT INTO person (id, name, hashedpassword) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.ID, user.USERNAME, user.HASHEDPASSWORD);
        System.out.println("Insertion successful");
    }
}