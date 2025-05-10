CREATE TABLE IF NOT EXISTS `ecommerce_db`.`order` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `tracking_no` VARCHAR(255) DEFAULT NULL,
    `total_quantity` INT DEFAULT NULL,
    `total_price` DECIMAL(13,2) DEFAULT NULL,
    `status` VARCHAR(255) DEFAULT NULL,
    `date_created` DATETIME(6) DEFAULT NULL,
    `last_updated` DATETIME(6) DEFAULT NULL,
    `shipping_address_id` BIGINT(20) NOT NULL,
    `billing_address_id` BIGINT(20) NULL,
    `customer_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_shipping_address` (`shipping_address_id`),
    KEY `fk_billing_address` (`billing_address_id`),
    KEY `fk_customer` (`customer_id`),
    CONSTRAINT `fk_shipping_address` FOREIGN KEY (`shipping_address_id`) REFERENCES `address` (`id`),
    CONSTRAINT `fk_billing_address` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
    CONSTRAINT `fk_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
    )
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;