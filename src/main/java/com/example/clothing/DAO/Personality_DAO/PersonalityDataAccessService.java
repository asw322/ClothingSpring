/**
 * Personality DAO that interacts with USER_PERSONALITY and PERSONALITY tables
 * 
 */

package com.example.clothing.DAO.Personality_DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.clothing.PersonalityToken;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.RowMapper;

@Component
public class PersonalityDataAccessService {
    private DriverManagerDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public PersonalityDataAccessService() {
        dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver"); 
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demodb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Get info on all different personalities
     * @return
     */
    public List<PersonalityToken> getAllPersonality(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<PersonalityToken>() {

            @Override
            public PersonalityToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                try {
                    return new PersonalityToken(rs.getString("personality_types"), rs.getString("personality_names"), Integer.parseInt(rs.getString("personality_count")));
                }
                catch(NumberFormatException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }


    /**
     * Get info on one specific personality 
     * @param String
     * @return 
     */
    public List<PersonalityToken> getPersonality(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<PersonalityToken>() {

            @Override
            public PersonalityToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                try {
                    return new PersonalityToken(rs.getString("personality_types"), rs.getString("personality_names"), Integer.parseInt(rs.getString("personality_count")));
                }
                catch(NumberFormatException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }


    /**
     * Get the most popular personality type
     */
    public List<PersonalityToken> getMostPopularPersonality(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<PersonalityToken>() {

            @Override
            public PersonalityToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                try {
                    return new PersonalityToken(rs.getString("personality_types"), rs.getString("personality_names"), Integer.parseInt(rs.getString("personality_count")));
                }
                catch(NumberFormatException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }



    /**
     * THESE METHODS INTEGRATE BOTH USER_PERSONALITY AND PERSONALITY TO GET USER DATA
     */
    public List<PersonalityToken> getPersonalityStrength(String sql) {
        return jdbcTemplate.query(sql, new RowMapper<PersonalityToken>() {

            @Override
            public PersonalityToken mapRow(ResultSet rs, int rowNumber) throws SQLException {
                PersonalityToken temp = new PersonalityToken(rs.getString("personality_types"), rs.getString("personality_names"), rs.getInt("personality_count"));
                temp.PERSONALITY_STRENGTH = rs.getInt("personality_strength");
                return temp;
            }
        });
    }
}
