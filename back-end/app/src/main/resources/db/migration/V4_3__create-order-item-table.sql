CREATE TABLE IF NOT EXISTS `ecommerce_db`.`order_item` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `img_url` VARCHAR(255) DEFAULT NULL,
    `quantity` INT DEFAULT NULL,
    `unit_price` DECIMAL(13,2) DEFAULT NULL,
    `order_id` BIGINT(20) NOT NULL,
    `product_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_order` (`order_id`),
    KEY `fk_product` (`product_id`),
    CONSTRAINT `fk_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
    CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
    )
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;