package com.su.cvsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
public class CvSearchApplication {


    public static void main(String[] args) {
        SpringApplication.run(CvSearchApplication.class, args);
    }
}
