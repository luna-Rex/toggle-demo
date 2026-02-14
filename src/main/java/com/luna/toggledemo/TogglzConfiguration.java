package com.luna.toggledemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.spring.security.SpringSecurityUserProvider;

import javax.sql.DataSource;

@Configuration
public class TogglzConfiguration {

    @Bean
    public StateRepository stateRepository(DataSource dataSource) {
        return new JDBCStateRepository(dataSource, "demo_togglz");
    }

    @Bean
    public UserProvider userProvider() {
        // 核心：告訴 Togglz 誰是當前登入者，以便進行 User-specific 的控制！🌙
        return new SpringSecurityUserProvider("ROLE_ADMIN");
    }
}
