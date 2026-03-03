package com.luna.toggledemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/db")
public class DatabaseInitController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/init")
    public Map<String, Object> initializeDatabase() {
        Map<String, Object> response = new HashMap<>();
        
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            
            // 檢查 demo_togglz 資料表是否存在 (H2 通常大寫處理)
            try (ResultSet resultSet = metaData.getTables(null, null, "DEMO_TOGGLZ", new String[]{"TABLE"})) {
                if (resultSet.next()) {
                    response.put("status", "success");
                    response.put("message", "Database is already initialized. Table 'demo_togglz' exists.");
                    return response;
                }
            }

            // 若不存在，執行資源資料夾中的 init.sql
            ClassPathResource resource = new ClassPathResource("db/init.sql");
            ScriptUtils.executeSqlScript(connection, resource);
            
            response.put("status", "success");
            response.put("message", "Database initialized successfully with Togglz schema and demo data.");
            
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Database initialization failed: " + e.getMessage());
        }
        
        return response;
    }
}
