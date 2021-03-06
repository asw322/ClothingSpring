package com.example.clothing.DAO.User_DAO;

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
import com.example.clothing.DAO.Clothing_DAO.ClothingDataAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Component
public class UserDataAccessService {

    // @Autowired
    // private DriverManagerDataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    private ClothingDataAccessService CLOTHING_DATA_ACCESS_SERVICE;

    private ProductToken productToken;


    public UserDataAccessService() {
        // dataSource = new DriverManagerDataSource();
        // dataSource.setDriverClassName("org.postgresql.Driver"); 
        // dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
        // dataSource.setUsername("postgres");
        // dataSource.setPassword("password");

        // jdbcTemplate = new JdbcTemplate(dataSource);
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
                temp.setLogin(rs.getString("name"), rs.getString("hashedpassword"), rs.getInt("id"));
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
                temp.setLogin(rs.getString("username"), rs.getString("hashedpassword"), rs.getInt("id"));
                return temp;
            }
        });
    }

    public List<UserToken> getUserData(String sql, String name, String hashedpassword) {
        return namedJdbcTemplate.query(
            sql,
            new MapSqlParameterSource()
                .addValue("username", name)
                .addValue("hashedpassword", hashedpassword),
            new RowMapper<UserToken>() {

                @Override
                public UserToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                    UserToken temp = new UserToken();
                    temp.setLogin(rs.getString("username"), rs.getString("hashedpassword"), rs.getInt("id"));
                    return temp;
                }
            }
        );
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
    public List<ProductToken> getUserOwnedProductDetail(String sql, int id) {
        return namedJdbcTemplate.query(
            sql, 
            new MapSqlParameterSource()
                .addValue("id", id),
            new RowMapper<ProductToken>() {
            
                @Override
                public ProductToken mapRow(ResultSet rs, int rowNumber) throws SQLException {

                    // NOTEEEE: THIS IS A FUNCTIONALITY STOLEN FROM ClothingDataAccessService
                    // Figure out how to inline that function into here
                    return new ProductToken(
                        rs.getString("product_id"),
                        rs.getString("manufacturer_name"),
                        rs.getString("product_reference_number"),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getDouble("price_in_dollars"),
                        rs.getString("product_length"),
                        rs.getString("product_height"),
                        rs.getString("product_width"),
                        rs.getString("product_style"),
                        rs.getString("product_color"),
                        rs.getString("product_url"),
                        null
                    );
                }
        });
    }


    
    public List<Object[]> getUserOwnedProductDetailWithWearCount(String sql, int id) {
        return namedJdbcTemplate.query(
            sql, 
            new MapSqlParameterSource()
                .addValue("id", id),
            new RowMapper<Object[]>() {
            
                @Override
                public Object[] mapRow(ResultSet rs, int rowNumber) throws SQLException {

                    ProductToken temp = new ProductToken(
                        rs.getString("product_id"),
                        rs.getString("manufacturer_name"),
                        rs.getString("product_reference_number"),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getDouble("price_in_dollars"),
                        rs.getString("product_length"),
                        rs.getString("product_height"),
                        rs.getString("product_width"),
                        rs.getString("product_style"),
                        rs.getString("product_color"),
                        rs.getString("product_url"),
                        null
                    );

                    Object[] res = {temp, rs.getString("wear_count")};

                    return res;
                }
        });
    }


    /**
     * Generates a new ID by taking the highest ID + 1 
     */
    // public int generateNewID() {
    //     final String sql = "SELECT MAX(id) AS largestId FROM person";
    //     List<Integer> res= jdbcTemplate.query(sql, new RowMapper<Integer>() {
            
    //         @Override
    //         public Integer mapRow(ResultSet rs, int rowNumber) throws SQLException {
    //             return Integer.parseInt(rs.getString("largestId"));
    //         }
    //     });

    //     return (res.get(0) + 1);
    // }

    public List<Integer> getHighestID(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<Integer>() {
            
            @Override
            public Integer mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return Integer.parseInt(rs.getString("largestId"));
            }
        });
    }



    /**
     * Inserting a user user into the person table
     */

    // public int insertNewUser(String sql) {        
    //     return jdbcTemplate.update(sql);
    // }
    public int insertNewUser(String sql, int id, String username, String hashedpassword) {
        return namedJdbcTemplate.update(
            sql, 
            new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("username", username)
                .addValue("hashedpassword", hashedpassword)
        );
    }
}