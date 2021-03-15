CREATE DATABASE IF NOT EXISTS prowayeventsmanager_db;

USE prowayeventsmanager_db;

# Event Room
SELECT * FROM prowayeventsmanager_db.tbEventRoom;

INSERT INTO tbEventRoom (idEventRoom, name, capacity) values (1, 'sala 1', 20);
INSERT INTO tbEventRoom (idEventRoom, name, capacity) values (2, 'sala 2', 10);

# Coffee Room
SELECT * FROM prowayeventsmanager_db.tbCoffeeRoom;

INSERT INTO tbCoffeeRoom (idCoffeeRoom, name) values (1, 'Saguão 01');
INSERT INTO tbCoffeeRoom (idCoffeeRoom, name) values (2, 'Saguão 02');

# Person
SELECT * FROM prowayeventsmanager_db.tbPerson;

INSERT INTO tbPerson (idPerson, name, lastname, seat) values (1, 'Larine','Arleta', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (2, 'Ondrea', 'Michelle', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (3, 'Lotty', 'Franky', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (4, 'Gelya', 'Laetitia', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (5, 'Lindsay', 'Cammy', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (6, 'Lanni', 'Bert', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (7, 'Kirsten', 'Lilia', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (8, 'Helena', 'Maribelle', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (9, 'Dot', 'Audry', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (10, 'Delinda', 'Mignon', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (11, 'Ruth', 'Carolan', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (12, 'Lula', 'Janna', 0);