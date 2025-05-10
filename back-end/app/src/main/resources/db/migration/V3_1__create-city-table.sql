CREATE TABLE IF NOT EXISTS `ecommerce_db`.`city` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `country_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`country_id`) REFERENCES `country`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB AUTO_INCREMENT = 1;

INSERT INTO `ecommerce_db`.`city` (`name`, `country_id`) VALUES
     -- United States
     ('New York', 1),
     ('Los Angeles', 1),
     ('Chicago', 1),
     ('Houston', 1),
     ('Phoenix', 1),
     ('Philadelphia', 1),
     ('San Antonio', 1),
     ('San Diego', 1),
     ('Dallas', 1),
     ('San Jose', 1),

     -- Egypt
     ('Cairo', 2),
     ('Alexandria', 2),
     ('Giza', 2),
     ('Port Said', 2),
     ('Suez', 2),
     ('Luxor', 2),
     ('Aswan', 2),
     ('Tanta', 2),
     ('Mansoura', 2),
     ('Zagazig', 2),

     -- Germany
     ('Berlin', 3),
     ('Hamburg', 3),
     ('Munich', 3),
     ('Cologne', 3),
     ('Frankfurt', 3),
     ('Stuttgart', 3),
     ('Düsseldorf', 3),
     ('Dortmund', 3),
     ('Essen', 3),
     ('Leipzig', 3),

     -- Japan
     ('Tokyo', 4),
     ('Yokohama', 4),
     ('Osaka', 4),
     ('Nagoya', 4),
     ('Sapporo', 4),
     ('Kobe', 4),
     ('Kyoto', 4),
     ('Fukuoka', 4),
     ('Kawasaki', 4),
     ('Saitama', 4),

     -- Brazil
     ('São Paulo', 5),
     ('Rio de Janeiro', 5),
     ('Salvador', 5),
     ('Fortaleza', 5),
     ('Belo Horizonte', 5),
     ('Brasília', 5),
     ('Curitiba', 5),
     ('Manaus', 5),
     ('Recife', 5),
     ('Belém', 5);
