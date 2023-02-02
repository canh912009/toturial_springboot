package com.example2.toturial.database;

import com.example2.toturial.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Now connect with mysql using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD=123456 \
-e MYSQL_USER=canhnx \
-e MYSQL_PASSWORD=123456 \
-e MYSQL_DATABASE=test_db \
-p 3309:3306 \
--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
mysql:latest
-----------

For Docker windown tren 1 dong thoi : docker run -d --rm --name mysql-spring-boot-tutorial -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=canhnx -e MYSQL_PASSWORD=123456 -e MYSQL_DATABASE=test_db -p 3309:3306 --volume mysql-spring-boot-tutorial-volume:\\wsl$\docker-desktop-data\version-pack-data\community\mysql\ mysql:latest
 --> More generally /var/lib/docker/ maps to \\wsl$\docker-desktop-data\version-pack-data\community\docker\

docker exec -it mysql-spring-boot-tutorial bash

connect db : mysql -h localhost -p 3309 --protocol=tcp -u canhnx -p

mysql -u canhnx -p
* */

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product productA = new Product("Mac ", 2021, 2400.0, "");
//                Product productB = new Product("Mac 2", 2021, 2400.0, "");
//
//                logger.info("insert data : " +productRepository.save(productA));
//                logger.info("insert data : " +productRepository.save(productB));
            }
        };
    }
}
