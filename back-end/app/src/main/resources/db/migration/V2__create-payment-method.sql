CREATE TABLE IF NOT EXISTS `ecommerce_db`.`payment_method` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `method_name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

INSERT INTO `ecommerce_db`.`payment_method` (`method_name`)
VALUES
    ('Cash on Delivery'),
    ('Visa/Master Card'),
    ('Apple Pay'),
    ('Google Pay');
