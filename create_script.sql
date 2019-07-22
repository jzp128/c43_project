DROP DATABASE IF EXISTS airbnb;
CREATE DATABASE IF NOT EXISTS airbnb;
-- SHOW DATABASES;
USE airbnb;
-- SELECT DATABASE();

CREATE TABLE IF NOT EXISTS users (
	sin			CHAR(9)			NOT NULL,
    userName 	VARCHAR(100) 	NOT NULL DEFAULT '',
    dob 		DATETIME 		NOT NULL,
    occupation 	VARCHAR(100) 	NOT NULL DEFAULT 'NONE',
    primary key(sin)
);

CREATE TABLE IF NOT EXISTS amendities(
	amendID INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    amendName VARCHAR(100),
    amendDescription VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS listing (
	listingID INT UNSIGNED  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
    price DECIMAL(65,2) UNSIGNED NOT NULL DEFAULT '0.00',-- should we have this in here?
    listingType CHAR(10) NOT NULL DEFAULT 'UNKNOWN',
    longitude DOUBLE NOT NULL DEFAULT '0.0',
    latitude DOUBLE NOT NULL DEFAULT '0.0',
    -- start date
    -- end date
    -- does this work for amendties? or do we need another table
    FOREIGN KEY fk_amendId(amendID)
    REFERENCES amendities (amendID)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);



CREATE TABLE IF NOT EXISTS renters(
	renterID CHAR(9) PRIMARY KEY REFERENCES user (userID),
    ccNumber char(16) NOT NULL,
    ccSecNum char(3) NOT NULL,
    ccCardName varchar(50) NOT NULL DEFAULT ''
);