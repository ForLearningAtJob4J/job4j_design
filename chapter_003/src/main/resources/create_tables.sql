CREATE TABLE roles (
	id serial primary key,
	name varchar(1024)
);

CREATE TABLE rules (
	id serial primary key,
	name varchar(1024)
);

CREATE TABLE role_rule_links (
	id_role integer not NULL,
	id_rule integer not NULL,
	foreign key (id_role) references roles(id) ON UPDATE CASCADE ON DELETE CASCADE,
	foreign key (id_rule) references rules(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE users (
	id serial primary key,
	id_role int REFERENCES roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
	login varchar(32),
	password varchar(16),
	name varchar(1024)
);

CREATE TABLE states (
	id serial primary key,
	name varchar(1024)
);

CREATE TABLE categories (
	id serial primary key,
	name varchar(1024)
);

CREATE TABLE items (
	id serial primary key,
	description VARCHAR(1024),
	text TEXT,
	id_category int REFERENCES categories (id) ON UPDATE CASCADE ON DELETE CASCADE,
	id_state int REFERENCES states (id) ON UPDATE CASCADE ON DELETE CASCADE,
	id_user integer REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE attaches (
	id serial primary key,
	id_item integer REFERENCES items (id) ON UPDATE CASCADE ON DELETE CASCADE,
	name VARCHAR(1024)
);

CREATE TABLE comments (
	id serial primary key,
	id_item integer REFERENCES items (id) ON UPDATE CASCADE ON DELETE CASCADE,
	comment VARCHAR(1024)
);