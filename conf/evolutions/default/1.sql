# Category schema

# --- !Ups

CREATE TABLE category (
    id integer NOT NULL AUTO_INCREMENT,
    label varchar(50) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE category;
