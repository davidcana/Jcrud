CREATE USER jcrud_user WITH PASSWORD 'emkv4df2lp';

/* Simple */
DROP TABLE simple CASCADE;
CREATE TABLE simple (
    id integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL,
    description text
);
GRANT SELECT, INSERT, UPDATE, DELETE ON simple TO jcrud_user;

DROP TABLE simple2 CASCADE;
CREATE TABLE simple2 (
    id integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL,
    description text
);
GRANT SELECT, INSERT, UPDATE, DELETE ON simple2 TO jcrud_user;

DROP TABLE simple2_detail CASCADE;
CREATE TABLE simple2_detail (
	id char(10) PRIMARY KEY,
    simple2_id integer REFERENCES simple2 NOT NULL,
    name varchar(100) UNIQUE NOT NULL,
    description text
);
GRANT SELECT, INSERT, UPDATE, DELETE ON simple2_detail TO jcrud_user;

DROP TABLE simple2_detail2 CASCADE;
CREATE TABLE simple2_detail2 (
	id char(10) PRIMARY KEY,
    simple2_id integer REFERENCES simple NOT NULL,
    name varchar(100) UNIQUE NOT NULL,
    description text
);
GRANT SELECT, INSERT, UPDATE, DELETE ON simple2_detail2 TO jcrud_user;

/* Complex */
DROP TABLE secondary CASCADE;
CREATE TABLE secondary (
    id integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL
);
GRANT SELECT, INSERT, UPDATE, DELETE ON secondary TO jcrud_user;

DROP TABLE complex CASCADE;
CREATE TABLE complex (
	id integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL,
	description text, 
    secondary_id integer REFERENCES secondary NOT NULL,
	important boolean,
	number_float real,
    number_integer integer,
    birth date NOT NULL,
	record_time time NOT NULL,
	record_date_time timestamp NOT NULL
);
GRANT SELECT, INSERT, UPDATE, DELETE ON complex TO jcrud_user;

DROP TABLE complex2 CASCADE;
CREATE TABLE complex2 (
	id integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL,
	description text, 
    secondary_id integer REFERENCES secondary NOT NULL,
	important boolean,
	number_float real,
    number_integer integer,
    birth date NOT NULL,
	record_time time NOT NULL,
	record_date_time timestamp NOT NULL
);
GRANT SELECT, INSERT, UPDATE, DELETE ON complex2 TO jcrud_user;

DROP TABLE complex2_detail CASCADE;
CREATE TABLE complex2_detail (
	id char(10) PRIMARY KEY,
    complex2_id integer REFERENCES complex2 NOT NULL,
    name varchar(100) UNIQUE NOT NULL,
	description text, 
    secondary_id integer REFERENCES secondary NOT NULL,
	important boolean,
	number_float real,
    number_integer integer,
    birth date NOT NULL,
	record_time time NOT NULL,
	record_date_time timestamp NOT NULL
);
GRANT SELECT, INSERT, UPDATE, DELETE ON complex2_detail TO jcrud_user;

DROP TABLE complex2_detail2 CASCADE;
CREATE TABLE complex2_detail2 (
	id char(10) PRIMARY KEY,
    complex2_id integer REFERENCES complex2 NOT NULL,
    name varchar(100) UNIQUE NOT NULL,
	description text, 
    secondary_id integer REFERENCES secondary NOT NULL,
	important boolean,
	number_float real,
    number_integer integer,
    birth date NOT NULL,
	record_time time NOT NULL,
	record_date_time timestamp NOT NULL
);
GRANT SELECT, INSERT, UPDATE, DELETE ON complex2_detail2 TO jcrud_user;

/* Members */
DROP TABLE original_members CASCADE;
CREATE TABLE original_members (
	id integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL,
	record_datetime timestamp NOT NULL,
    country integer
);
GRANT SELECT, INSERT, UPDATE, DELETE ON original_members TO jcrud_user;

DROP TABLE verified_members CASCADE;
CREATE TABLE verified_members (
	id integer PRIMARY KEY,
    name varchar(100) UNIQUE NOT NULL,
	record_datetime timestamp NOT NULL,
    country integer
);
GRANT SELECT, INSERT, UPDATE, DELETE ON verified_members TO jcrud_user;

