package org.example;

import org.example.ui.UiManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {


        ApplicationContext context= SpringApplication.run(App.class, args);

        UiManager uiManager = new UiManager(context);

        uiManager.run();


       /*  DataSource dataSource = context.getBean(DataSource.class);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            System.out.println("success");
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT id, title, author FROM book");
            System.out.println("-----------------------");
            while(resultSet.next()) {
                System.out.println(resultSet.getLong("id") + " | " + resultSet.getString("title") + " | " + resultSet.getString("author"));
            }
            System.out.println("-----------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!= null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

    }
}
