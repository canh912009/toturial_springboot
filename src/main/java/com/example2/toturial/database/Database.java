package com.example2.toturial.database;

import com.example2.toturial.models.Product;
import com.example2.toturial.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Mac ", 2021, 2400.0, "");
                Product productB = new Product("Mac 2", 2021, 2400.0, "");

                logger.info("insert data : " +productRepository.save(productA));
                logger.info("insert data : " +productRepository.save(productB));
            }
        };
    }
}
