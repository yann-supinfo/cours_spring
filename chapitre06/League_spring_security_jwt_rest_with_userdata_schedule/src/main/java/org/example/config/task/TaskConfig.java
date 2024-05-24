package org.example.config.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class TaskConfig {

    // ms
    @Scheduled(fixedRate = 1000)
    public void myTask() {
        System.out.println("hello i'm a scheduled task");
    }

    @Scheduled(cron = "0 5 15 * * ?")
    public void myCronTask() {
        System.out.println("hello i'm a scheduled cron task");
    }

}
