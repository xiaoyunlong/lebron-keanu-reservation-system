DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS parkingPosition;

CREATE TABLE parkingPosition(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  parkingSpace INT NOT NULL,
  status tinyint NOT NULL
);

CREATE TABLE Orders (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  parkingLot VARCHAR(100) NOT NULL,
  parkingPosition INT NOT NULL,
  parkTime DATE NOT NULL
);

INSERT INTO parkingPosition (parkingSpace,status) VALUES
(10,1),
(20,0)
