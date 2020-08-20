DROP TABLE IF EXISTS orders;
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
  reserve_time DATE NOT NULL,
  start_time DATE NOT NULL,
  end_time DATE NOT NULL
);

INSERT INTO parkingPosition (parking_space,status) VALUES
(10,1),
(20,0)
