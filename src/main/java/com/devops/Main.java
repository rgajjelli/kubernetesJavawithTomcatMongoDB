package com.devops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main extends SpringBootServletInitializer {

    @Autowired
    MongoService mongoLoggerSerive;

    @RequestMapping("/")
    public String home() {
        mongoLoggerSerive.logToMongo(new LogRecord("INFO", "New Home page hit"));
        return "Welcome to SpringBoot with MongoDB example, initialiation of container is successful. <P>" +
                mongoLoggerSerive.getMongoDbInfo();
    }

    @RequestMapping("/push")
    public String getHomePageHits() {
        long homePageHitCount = mongoLoggerSerive.getHomePageHitCount();
        return "SpringBoot with MongoDB - Checking the hit counts - " +  homePageHitCount + " times";
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Main.class); //web.xml
    }

}
