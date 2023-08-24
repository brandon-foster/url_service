CREATE SCHEMA IF NOT EXISTS `urlservice`;

CREATE TABLE IF NOT EXISTS `urlservice`.`available_url` (
    `hash` VARCHAR(6) NOT NULL,
    `original_url` VARCHAR(2000) NOT NULL,
    PRIMARY KEY (`hash`)
);

-- CREATE TABLE IF NOT EXISTS `urlservice`.`unavailable_url` (
--     `user_id` INTEGER NOT NULL,
--     `email` VARCHAR(200) NULL,
--     `creation_date` DATE NOT NULL,
--     `last_login` DATE NOT NULL,
--     PRIMARY KEY (`user_id`)
-- );
