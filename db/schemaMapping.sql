create table engine (
  id serial primary key
);

create table car (
  id serial primary key,
  engine_id int not null unique
);

create table history_owner (
  id serial primary key 
);

create table car_history_owner (
  car_id serial,
  history_owner_id serial,
  primary key (car_id, history_owner_id),
  foreign key (car_id) references car(id),
  foreign key (history_owner_id) references history_owner(id)
);

create table driver (
	id serial primary key,
	history_owner_id serial
);

create table car_driver (
  car_id serial,
  driver_id serial,
  primary key (car_id, driver_id),
  foreign key (car_id) references car(id),
  foreign key (driver_id) references driver(id)
);

ALTER TABLE driver 
ADD CONSTRAINT HISTORY_OWNER_ID_FK
FOREIGN KEY (history_owner_id) references history_owner(id);

ALTER TABLE car
ADD CONSTRAINT ENGINE_ID_FK
FOREIGN KEY (engine_id) references engine(id);