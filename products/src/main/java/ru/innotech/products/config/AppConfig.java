package ru.innotech.products.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.Connection;

@Configuration
@PropertySource("classpath:db/jdbc.properties")
public class AppConfig {
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            var hc = new HikariConfig();
            hc.setJdbcUrl(url);
            hc.setDriverClassName(driverClassName);
            hc.setUsername(username);
            hc.setPassword(password);
            var dataSource = new HikariDataSource(hc);
            dataSource.setMaximumPoolSize(25);
            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException("Hikari DataSource бин не создан: " + e.getMessage());
        }
    }

    @Bean
    @DependsOn("dataSource")
    public Connection getConnection() {
        try {
            return dataSource().getConnection();
        } catch (Exception e) {
            throw new RuntimeException("Соединение с базой не создано: " + e.getMessage());
        }
    }
}