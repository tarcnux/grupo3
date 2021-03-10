CREATE USER 'mysql'@'localhost' IDENTIFIED BY 'mysql';

GRANT ALL PRIVILEGES ON * . * TO 'mysql'@'localhost';

FLUSH PRIVILEGES;