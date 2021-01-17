package com.example.clothing.DAO.Clothing_DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

import com.example.clothing.ProductToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class ClothingDataAccessService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private ProductToken productToken; 

    public ClothingDataAccessService() {
        // Empty
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
                    rs.getString("product_url"),
                    null                            // Still need to retrieve picture images 
                );
            }
        });
    }

    public List<ProductToken> getProductDetail(String sql, String product_info) {
        return namedJdbcTemplate.query(
            sql, 
            new MapSqlParameterSource()
                .addValue("product_info", product_info),
            new RowMapper<ProductToken>() {

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
                    rs.getString("product_url"),
                    null                            // Still need to retrieve picture images 
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
    // Test: this might not work since product_id are in the form 'P#'
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


    public List<String> getHighestPID(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return rs.getString("product_id");
            }
        });
    }



    public int executeUpdate(String sql) {
        return jdbcTemplate.update(sql);
    }
    public int executeUpdate(String sql, String product_id, String manufacturer_name, String product_reference_number, String product_name, String product_description, double price_in_dollars, String product_length, String product_height, String product_width, String product_style, String product_color, String product_url) {
        return namedJdbcTemplate.update(
            sql,
            new MapSqlParameterSource()
                .addValue("product_id", product_id)
                .addValue("manufacturer_name", manufacturer_name)
                .addValue("product_reference_number", product_reference_number)
                .addValue("product_name", product_name)
                .addValue("product_description", product_description)
                .addValue("price_in_dollars", price_in_dollars)
                .addValue("product_length", product_length)
                .addValue("product_height", product_height)
                .addValue("product_width", product_width)
                .addValue("product_style", product_style)
                .addValue("product_color", product_color)
                .addValue("product_url", product_url)
        );
    }
    public int executeUpdate(String sql, String product_id, String product_image) {
        return namedJdbcTemplate.update(
            sql,
            new MapSqlParameterSource()
                .addValue("product_id", product_id)
                .addValue("product_image", product_image)
        );
    }
}
