package org.example;

import org.example.ui.UiManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");

        UiManager uiManager = new UiManager(context);

        uiManager.run();
    }
}
