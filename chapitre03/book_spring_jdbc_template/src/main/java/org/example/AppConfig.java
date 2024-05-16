package org.example;

import org.example.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {

        };
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String activeProfile = env.getProperty("spring.profiles.active");
        String databaseName = activeProfile.equals("dev") ? "book_base_dev" : "book_base_prod";

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false");
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    public DataSourceInitializer schemaInitializer(DataSource dataSource) {
        System.out.println("dataSourceInitializer TRIGGER !!!");

        System.out.println("--------- TRIGGER ------");
        String activeProfile = env.getProperty("spring.profiles.active");
        String databaseName = activeProfile.equals("dev") ? "book_base_dev" : "book_base_prod";

        String url = "jdbc:mysql://localhost:3306/?useSSL=false";
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String createDatabase = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            statement.executeUpdate(createDatabase);

            System.out.println("Database '" + databaseName + "' created if not exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResourceDatabasePopulator schemaPopulator = new ResourceDatabasePopulator();




        if ("dev".equals(activeProfile)) {
            schemaPopulator.addScript(new ClassPathResource("schema-dev.sql"));

        } else if ("prod".equals(activeProfile)) {
            schemaPopulator.addScript(new ClassPathResource("schema-prod.sql"));

        }

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(schemaPopulator);


        return initializer;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        System.out.println("dataSourceInitializer TRIGGER !!!");

        System.out.println("--------- TRIGGER ------");
        String activeProfile = env.getProperty("spring.profiles.active");
        String databaseName = activeProfile.equals("dev") ? "book_base_dev" : "book_base_prod";

        String url = "jdbc:mysql://localhost:3306/?useSSL=false";
        String username = env.getProperty("spring.datasource.username");
        String password = env.getProperty("spring.datasource.password");

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            String createDatabase = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            statement.executeUpdate(createDatabase);

            System.out.println("Database '" + databaseName + "' created if not exists");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResourceDatabasePopulator dataPopulator = new ResourceDatabasePopulator();
        Boolean insertData = Boolean.parseBoolean(env.getProperty("insert-data"));

        if (insertData) {
            if ("dev".equals(activeProfile)) {

                dataPopulator.addScript(new ClassPathResource("data-dev.sql"));
            } else if ("prod".equals(activeProfile)) {

                dataPopulator.addScript(new ClassPathResource("data-prod.sql"));
            }
        }
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        if (insertData) {
            initializer.setDatabasePopulator(dataPopulator);
        }


        return initializer;
    }
}