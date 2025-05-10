CREATE TABLE IF NOT EXISTS `ecommerce_db`.`customer` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) DEFAULT NULL,
    `last_name` VARCHAR(255) DEFAULT NULL,
    `email` VARCHAR(255) DEFAULT NULL,
    `phone` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    )
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;