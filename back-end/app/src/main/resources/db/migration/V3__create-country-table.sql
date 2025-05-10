CREATE TABLE IF NOT EXISTS `ecommerce_db`.`country` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`))
    ENGINE=InnoDB
    AUTO_INCREMENT = 1;

INSERT INTO `ecommerce_db`.`country` (`code`, `name`) VALUES
  ('US', 'United States'),
  ('EG', 'Egypt'),
  ('DE', 'Germany'),
  ('JP', 'Japan'),
  ('BR', 'Brazil');
