CREATE SCHEMA IF NOT EXISTS `urlservice`;

CREATE TABLE IF NOT EXISTS `urlservice`.`url` (
    `hash` VARCHAR(6) NOT NULL,
    `original_url` VARCHAR(2000) NOT NULL,
    PRIMARY KEY (`hash`)
);
