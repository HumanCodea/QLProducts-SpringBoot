package com.storeTechnology.demo.cofig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.storeTechnology.demo.model.Products;
import com.storeTechnology.demo.reponsitories.ProductResponsitory;


@Configuration
public class Database {

    // giong voi sout nhung nang cao hon
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductResponsitory productResponsitory){ // fake data
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception{
        //     Products product1 = new Products("Iphonel 12 pro", 2023, "50 USD", "");
        //    Products product2 = new Products("Samsung ", 2019, "100 USD", "");
        //    logger.info("insert data: "+ productResponsitory.save(product1));
        //    logger.info("insert data: "+ productResponsitory.save(product2));

            }
        };

    }
}