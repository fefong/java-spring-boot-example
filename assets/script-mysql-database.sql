CREATE DATABASE db_springboot;

USE db_springboot;

DROP TABLE IF EXISTS client;
CREATE TABLE client (
	id INT NOT NULL  AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,    
    PRIMARY KEY(id)
);

INSERT INTO client (name)
VALUES
	('Client 1'), ('Client 2'), ('Client 3');
    
SELECT * FROM client;
