CREATE TABLE types (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE rules (
    id serial primary key,
    name varchar(2000)
);

CREATE TABLE accident (
    id serial primary key,
    name varchar(2000),
    text varchar(2000),
    address varchar(2000),
    type_id INT REFERENCES types(id),
    rules varchar(2000)
);

INSERT INTO types(name) VALUES('Две машины');
INSERT INTO types(name) VALUES('Машина и человек');
INSERT INTO types(name) VALUES('Машина и велосипед');
INSERT INTO types(name) VALUES('Машина и олень');
INSERT INTO types(name) VALUES('Человек и велосипед');

INSERT INTO rules(name) VALUES('Статья 1.1');
INSERT INTO rules(name) VALUES('Статья 1.2');
INSERT INTO rules(name) VALUES('Статья 1.3');
INSERT INTO rules(name) VALUES('Статья 2.1');
INSERT INTO rules(name) VALUES('Статья 2.2');




