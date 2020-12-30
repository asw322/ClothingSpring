package com.example.clothing.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import com.example.clothing.ProductToken;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class ClothingDataAccessService {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private ProductToken productToken; 

    public ClothingDataAccessService() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ProductToken> getProductDetail(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<ProductToken>() {

            @Override
            public ProductToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
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
                    rs.getString("product_url")
                );
            }
        });
    }


    /**
     * Input: product_name
     * Output: list of product_id
     * @param sql
     * @return
     */
    public List<Integer> getProductIDOnProductName(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<Integer>() {

            @Override
            public Integer mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return Integer.parseInt(rs.getString("product_id"));
            }
        });
    }


    /**
     * Input: product_name 
     * Output: ProductToken with product_length, product_height, product_width
     */
    public List<ProductToken> getProductDimensionsOnProductName(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<ProductToken>() {
            
            @Override
            public ProductToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                ProductToken temp = new ProductToken();
                temp.setDimensions(rs.getString("product_length"), rs.getString("product_height"), rs.getString("product_width"));
                return temp;
            }
        });
    }


    /**
     * Input: product_name 
     * Output: product_url
     */
    public List<String> getProductURLOnProductName(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return rs.getString("product_url");
            }
        });
    }


    /**
     * Input: product_name 
     * Output: product_color 
     */

    public List<String> getProductColorOnProductName(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return rs.getString("product_color");
            }
        });
    }

    
    /**
     * Input: product_name 
     * Output: product_description
     */

    public List<String> getProductDescriptionOnProductName(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return rs.getString("product_description");
            }
        });
    }



    /**
     * Input: product_name 
     * Output: manufacturer_name
     */
    public List<String> getManufacturerNameOnProductName(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return rs.getString("manufacturer_name");
            }
        });
    }
}
