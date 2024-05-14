package org.example;

import org.example.ui.UiManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context= SpringApplication.run(App.class, args);

        UiManager uiManager = new UiManager(context);

        uiManager.run();
    }
}
