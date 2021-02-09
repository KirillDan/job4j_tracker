create table items (
  id serial primary key;
  name varchar(2000)
);

create table cars(
    int id serial primary key,
    model varchar(255),
    created timestamp
);

create table j_role (
    id serial primary key,
    name varchar(2000)
);

create table j_user (
    id serial primary key,
    name varchar(2000),
    role_id int not null references j_role(id)
);