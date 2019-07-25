DROP DATABASE IF EXISTS airbnb;
CREATE DATABASE IF NOT EXISTS airbnb;
-- SHOW DATABASES;
USE airbnb;
-- SELECT DATABASE();
CREATE TABLE IF NOT EXISTS users (
	userID		INT UNSIGNED auto_increment primary key,
	sin			CHAR(9)			NOT NULL,
    userName 	VARCHAR(100) 	NOT NULL DEFAULT '',
    dob 		DATETIME 		NOT NULL,
    occupation 	VARCHAR(100) 	NOT NULL DEFAULT 'NONE',
    loginName	VARCHAR(20) UNIQUE NOT NULL DEFAULT '',
    loginPW		VARCHAR(20) NOT NULL DEFAULT '',
    address VARCHAR(100) NOT NULL,
    country VARCHAR(30) NOT NULL DEFAULT '',
    city VARCHAR(30) NOT NULL DEFAULT '',
    postal_code VARCHAR(30) NOT NULL DEFAULT '',
    isHoster BOOL NOT NULL DEFAULT FALSE
);

-- CREATE TABLE IF NOT EXISTS hosters(
-- hosterID INT UNSIGNED PRIMARY KEY REFERENCES users(userID)
-- );

CREATE TABLE IF NOT EXISTS renters(
	renterID INT UNSIGNED PRIMARY KEY REFERENCES users(userID),
    ccNumber char(16) NOT NULL,
    ccSecNum char(3) NOT NULL,
    ccCardName varchar(50) NOT NULL DEFAULT ''
);

CREATE TABLE IF NOT EXISTS amenities(
	amentID INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    amentName VARCHAR(100),
    amentDescription VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS listing (
	listingID INT UNSIGNED  NOT NULL  AUTO_INCREMENT  PRIMARY KEY,
    listingType VARCHAR(10) NOT NULL DEFAULT 'UNKNOWN',
    longitude DOUBLE NOT NULL DEFAULT '0.0',
    latitude DOUBLE NOT NULL DEFAULT '0.0',
    hosterID INT UNSIGNED,
    address VARCHAR(100) NOT NULL,
    country VARCHAR(30) NOT NULL DEFAULT '',
    city VARCHAR(30) NOT NULL DEFAULT '',
    postal_code VARCHAR(30) NOT NULL DEFAULT '',
    FOREIGN KEY (hosterID)
    REFERENCES users(userID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS amenitiesList(
	listingID int UNSIGNED,
    amentID INT UNSIGNED,
    FOREIGN KEY (listingID)
    REFERENCES listing(listingID)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (amentID)
    REFERENCES amenities(amentID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS available(
	availDate DATE NOT NULL,
    price	DECIMAL(65,2),
    listingID INT UNSIGNED,
    isBooked BOOL DEFAULT FALSE,
    FOREIGN KEY (listingID) REFERENCES listing(listingID)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bookings(
	bookingID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    hostID INT UNSIGNED,
    renterID INT UNSIGNED,
    isCanceled BOOLEAN NOT NULL DEFAULT (False),
    isHistory BOOLEAN NOT NULL DEFAULT (False),
    fromDate DATE,
    toDate DATE,
    FOREIGN KEY (hostID) references users(userID) ON UPDATE CASCADE ON DELETE SET NULL,
    foreign key (renterID) references renters(renterID) ON UPDATE CASCADE ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS reviews(
	creatorID INT UNSIGNED,
    receiverID INT UNSIGNED,
    listingID INT UNSIGNED,
    content longtext,
    rating decimal(2,1),
    reviewType CHAR(1)
);

-- set up the amenities table
INSERT INTO amenities (amentName, amentDescription)
VALUES
('Essentials', 'Towels, bed sheets, soap, toilet paper, and pillows'),
('Wifi', 'Free or paid wifi available'),
('Shampoo', 'Hair care product'),
('Hair dryer', 'A hair care tool to dry out hair'),
('Closet/drawers', 'Furniture for guests to put their clothes'),
('TV', 'Cable television or TV monitor'),
('Heat', 'A system that maintains a warm temperature'),
('Air Conditioning', 'A system that maintains a cool temperature'),
('Breakfast, coffee, tea', 'Complimentary items or paid'),
('Desk/workspace', 'Furniture for guests to work on'),
('Fireplace', 'Electronic or traditional wood burning'),
('Iron', 'A tool to smooth out clothes'),
('Private entrance', 'An entrance Guests will not need to share with anyone else'),
('Smoke detector', 'Check your local laws, which may require a working smoke detector in every room'),
('Carbon monoxide detector', 'Check your local laws, which may require a working carbon monoxide detector in every room'),
('First aid kit', 'A collection of supplies and equipment that is used to give medical treatment'),
('Fire extinguisher', 'This is highly recommended fire safety prevention measure'),
('Lock on bedroom door', 'Private room which can be locked for safety and privacy')
;