CREATE TABLE IF NOT EXISTS `ecommerce_db`.`address` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `street` VARCHAR(255) DEFAULT NULL,
    `country_id` BIGINT(20) NOT NULL,
    `city_id` BIGINT(20) NOT NULL,
    `zip_code` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_country` (`country_id`),
    KEY `fk_city` (`city_id`),
    CONSTRAINT `fk_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
    CONSTRAINT `fk_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
    )
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;