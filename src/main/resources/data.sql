DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS parkingPosition;

CREATE TABLE parkingPosition(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  parkingSpace INT NOT NULL,
  status tinyint NOT NULL
);

CREATE TABLE orders (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  parkingLot VARCHAR(100) NOT NULL,
  parkingPosition VARCHAR(100) NOT NULL,
  parkTime DATE not null
);

INSERT INTO parkingPosition (parkingSpace,status) VALUES
(10,1),
(20,0)