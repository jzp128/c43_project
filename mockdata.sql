


-- set up users table
INSERT INTO users (sin,userName,dob,occupation,loginName,loginPW,address,country,city,postal_code,isHoster)
VALUES
('987654321','Jacqueline-Chan','1990-01-17','Programmer','jacqueline','chan','22 Mum Court','Canada','Markham','L6C158',1),
('123456789','Haque-Yas','1991-01-17','Artist','haquey','asdf','22 Ber Court','Canada','Markham','L6C158',1),
('234567890','Andrew-Leung','1992-03-17','Driver','andrewl','sdfg','22 Son Court','Canada','Markham','L6C158',0),
('345678901','Jimmy-P','1993-06-17','Teacher','jimmy','pang','9 Courtway Street','Canada','Toronto','L8V2R7',1),
('456789012','Kevin-B','1994-05-17','Engineer','kevinb','qwe','2 House Street','Canada','Toronto','L8V2R5',1),
('567890123','Harsh-P','1995-08-17','Librarian','harshp','chan','21 Kigh Street','Canada','Toronto','L8V304',1),
('678901234','Pat-Li','1996-02-17','Animator','patl','123','20 Land Street','Canada','Toronto','L8V304',0),
('789012345','Fred-Pun','1997-01-17','Appraiser','fredp','gfd','212 Red Street','Canada','Ottawa','R8R2R4',0),
('890123456','David-Li','1998-01-17','Architect','davidl','qwe','39 Creek Street','Canada','Ottawa','R8R2R5',1),
('901234567','Nick-Cheng','1980-12-17','Therapist','nickc','zxc','978 Richard Drive','Canada','Ottawa','R8R2R6',0),
('876543219','Patricia-L','1981-11-17','Technician','patricial','cvbdfsd','2 Person Drive','Canada','Calgary','S8V2S4',0),
('765432198','Ricky-Chen','1982-10-17','Caterer','rickyc','jacqueline','2 Rouge Hill','Canada','Calgary','T8V2R4',0),
('654321987','Jayden-Arq','1983-10-17','Chocolatier','jaydena','jacqueline','2 Moore Hill','Canada','Richmond Hill','W8V2R4',1),
('543219876','Patrick-Star','1994-09-17','City Planner','patricks','chan','1 Celine Hill','Canada','Scarborough','M8V2M4',0),
('432109876','Jeff-So','1990-07-17','Civil Engineer','jeffs','lkj','97 Dion Way','Canada','Scarborough','M8V2M4',1),
('321098765','Jeff-Who','1994-05-17','Clothing Store Manager','asdf','who','8 Park Park','Canada','Richmond Hill','W8V5R4',0),
('210987654','Crystal-Yip','1997-01-17','Columnist','crystaly','password','29 Hiya Court','Canada','Saskatoon','K9M1Y4',1),
('123456789','Megan-Kun','1995-06-17','Community Organizer','megank','hello','22 Bum Court ','Canada','Saskatoon','K9M5I2',0),
('098765432','Jessica-Li','1952-01-17','Programmer','jessical','chan','55 Mumber Way','Canada','Saskatoon','K9M5I4',0),
('140793240','Phoebe-Liao','1961-04-17','Appraiser','phoebel','nurse','88 Hill Street','Canada','Quebec City','E9B6W1',1),
('234912304','Kitti-Chau','1993-03-17','Concierge','kittic','kitty','3 Street Street','Canada','Hamilton','J8V2J4',1),
('509821408','Jasmine-Lai','2000-01-17','Programmer','jasminel','lai','9 Love Street','Canada','Quebec City','E9B1Q3',0),
('302908230','Carol-Li','2000-04-17','Programmer','caroll','lee','11 Personnate Drive','Canada','Hamilton','J8V2K4',0),
('543030303','Christopher-Wai','1920-01-17','Teacher','christopherw','wai','23 Free Street','Canada','Hamilton','J8L2K4',0),
('444444444','Gary-Poon','1940-02-17','Librarian','garypoon','poon','21 Inno Court','Canada','Toronto','L8V100',1),
('999999999','Spongebob-Square','1912-01-17','Therapist','spongebobs','square','21 Inno Court','Canada','Toronto','L8V100',0),
('123456789','Bob-Steve','1993-05-17','Therapist','bobs','circle','6 Military Trial','Canada','Toronto','L8V2R4',0),
('123456789','Mister-Chen','1990-02-17','Programmer','misterc','chen','6 Military Trial','Canada','Toronto','L8V2R4',0);



-- set up the listing table
INSERT INTO listing (listingType, longitude, latitude, hosterID, address, country, city, postal_code)
VALUES
('Full House','123.12','423.12',5,'123 Hello','Canada','Markham','M1R 0E9'),
('Apartment','456.12','532.12',1,'123 How','Canada','Toronto','M3C 0C1'),
('Apartment','789.12','497.12',21,'123 Are','Canada','Toronto','M3C 0C2'),
('Room','23.12','341.12',15,'123 You','Canada','Montreal','M3C 0C3'),
('Full House','345.12','43.12',9,'123 Military','Canada','Quebec City','M3C 0E3'),
('Room','345.12','54.12',13,'123 Trial','Canada','Toronto','M3C 0E4'),
('Apartment','100.12','300.12',25,'123 Jacqueline','Canada','Ottawa','M3C 0H9'),
('Full House','213.12','123.12',2,'123 Jimmy','Canada','Ottawa','M3C 0J1'),
('Full House','313.12','232.12',4,'123 Andrew','Canada','Hamilton','J1R 0E9'),
('Apartment','250.12','142.12',6,'123 Qi','Canada','Toronto','Q1K 0E9'),
('Full House','352.12','400.12',5,'123 Nick','Canada','Saskatoon','K90 0J9'),
('Room','500.12','433.12',20,'123 Love','Canada','Calgary','J1R 0D9'),
('Room','235.12','253.12',17,'123 Yusef','Canada','Vancouver','D1R 0E9');