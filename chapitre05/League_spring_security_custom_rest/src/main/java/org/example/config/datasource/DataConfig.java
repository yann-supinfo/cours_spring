package org.example.config.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class DataConfig {

    private Environment env;

    public DataConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource datasource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String databaseName = env.getProperty("database.name");

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url") + databaseName + "?useSSL=false");
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public DataSourceInitializer schemaInitializer(DataSource dataSource) {

        String activeProfile = env.getProperty("spring.profiles.active");
        String databaseName = env.getProperty("database.name");
        String url = env.getProperty("spring.datasource.url") + "?useSSL=false";
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        try(Connection  connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResourceDatabasePopulator schemaPopulator = new ResourceDatabasePopulator();
        schemaPopulator.addScript(new ClassPathResource(env.getProperty("database.schema-initializer")));

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(schemaPopulator);

        return initializer;

    }


}
