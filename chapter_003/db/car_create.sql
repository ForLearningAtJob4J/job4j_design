DROP TABLE IF EXISTS Car;
DROP TABLE IF EXISTS Body;
DROP TABLE IF EXISTS Engine;
DROP TABLE IF EXISTS Transmission;

CREATE TABLE Body (
	id serial PRIMARY KEY,
	name VARCHAR(256)
);

CREATE TABLE Engine (
	id serial PRIMARY KEY,
	volume INTEGER
);

CREATE TABLE Transmission (
	id serial PRIMARY KEY,
	name VARCHAR(128)
);

CREATE TABLE Car (
	id serial PRIMARY KEY,
	name VARCHAR(200),
	id_body INTEGER REFERENCES Body (id),
	id_engine INTEGER REFERENCES Engine (id),
	id_transmission INTEGER REFERENCES Transmission (id)
);

INSERT INTO Body (id, name) VALUES
    (1, 'Седан'),
    (2, 'Хэтчбэк'),
    (3, 'Универсал'),
    (4, 'Внедорожник');

INSERT INTO Engine (id, volume) VALUES
    (1, 1500),
	(2, 1800),
    (3, 5000),
    (4, 10000);

INSERT INTO Transmission (id, name) VALUES
    (1, 'ручная'),
	(2, 'автоматическая'),
	(3, 'робот');

INSERT INTO Car (id, name, id_body, id_engine, id_transmission) VALUES
    (1, 'ВАЗ 21043', 2, 1, 1),
	(2, 'TOYOTA TUNDRA', 4, 3, 2),
    (3, 'HONDA ACCORD', 1, 2, 2);