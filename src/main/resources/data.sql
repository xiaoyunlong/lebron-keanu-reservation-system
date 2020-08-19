DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS parkingPosition;

CREATE TABLE parkingPosition(
  id INT AUTO_INCREMENT  PRIMARY KEY,
  parking_space INT NOT NULL,
  status tinyint NOT NULL
);

CREATE TABLE orders (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  parking_lot VARCHAR(100) NOT NULL,
  parking_position INT NOT NULL,
  park_time DATE NOT NULL
);

INSERT INTO parkingPosition (parkingSpace,status) VALUES
(10,1),
(20,0)
