package com.example.clothing;

import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.List;  
import org.springframework.dao.DataAccessException;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.ResultSetExtractor;  
import org.springframework.jdbc.core.RowMapper;  


public class EmployeeDao {
    private JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public List<Integer> getAllEmployeesRowMapper() {
        return template.query("select * from employee", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNumber) throws SQLException {
                return 2;
            }
        });
    }
}
