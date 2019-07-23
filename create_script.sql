DROP DATABASE IF EXISTS airbnb;
CREATE DATABASE IF NOT EXISTS airbnb;
-- SHOW DATABASES;
USE airbnb;
-- SELECT DATABASE();
CREATE TABLE IF NOT EXISTS address(
	addressID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    city	CHAR(50) NOT NULL,
    postal_code CHAR(20) NOT NULL,
    country CHAR(30) NOT NULL,
    street_name CHAR(50) NOT NULL,
    building_number char(10) NOT NULL,
    unit_number char(10)
);

CREATE TABLE IF NOT EXISTS users (
	userID		INT UNSIGNED auto_increment primary key,
	sin			CHAR(9)			NOT NULL,
    userName 	VARCHAR(100) 	NOT NULL DEFAULT '',
    dob 		DATETIME 		NOT NULL,
    occupation 	VARCHAR(100) 	NOT NULL DEFAULT 'NONE',
    loginName	VARCHAR(20) UNIQUE NOT NULL DEFAULT '',
    loginPW		VARCHAR(20) NOT NULL DEFAULT '',
    addressID INT UNSIGNED,
    FOREIGN KEY (addressID) references address(addressID)
);

CREATE TABLE IF NOT EXISTS hosters(
	hosterID INT UNSIGNED PRIMARY KEY REFERENCES users(userID)
);

CREATE TABLE IF NOT EXISTS renters(
	renterID INT UNSIGNED PRIMARY KEY REFERENCES users(userID),
    ccNumber char(16) NOT NULL,
    ccSecNum char(3) NOT NULL,
    ccCardName varchar(50) NOT NULL DEFAULT ''
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
    hosterID INT UNSIGNED,
    addressID INT UNSIGNED,
    FOREIGN KEY (hosterID)
    REFERENCES hosters(hosterID)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (addressID)
    REFERENCES address(addressID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS amenditiesList(
	listingID int UNSIGNED,
    amendID INT UNSIGNED,
    FOREIGN KEY (listingID)
    REFERENCES listing(listingID)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (amendID)
    REFERENCES amendities(amendID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS available(
	availDate DATE NOT NULL,
    price	DECIMAL(65,2),
    listingID INT UNSIGNED,
    FOREIGN KEY (listingID) REFERENCES listing(listingID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bookings(
	bookingID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    hostID INT UNSIGNED NOT NULL,
    renterID INT UNSIGNED NOT NULL,
    isCanceled BOOLEAN NOT NULL DEFAULT (False),
    isHistory BOOLEAN NOT NULL DEFAULT (False),
    fromDate DATE,
    toDate DATE,
    FOREIGN KEY (hostID) references hosters(hosterID) ON UPDATE CASCADE ON DELETE CASCADE,
    foreign key (renterID) references renters(renterID) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS reviews(
	creatorID INT UNSIGNED,
    receiverID INT UNSIGNED,
    listingID INT UNSIGNED,
    content longtext,
    rating decimal(2,1),
    reviewType CHAR(1)
);
