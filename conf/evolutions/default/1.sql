# Category schema

# --- !Ups

CREATE SEQUENCE category_id_seq;
CREATE TABLE category (
    id integer NOT NULL DEFAULT nextval('category_id_seq'),
    label varchar(50) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE category;
DROP SEQUENCE category_id_seq;