# Bookmark schema

# --- !Ups

CREATE SEQUENCE bookmark_id_seq;
CREATE TABLE bookmark (
    id integer NOT NULL DEFAULT nextval('bookmark_id_seq'),
    title varchar(50) NOT NULL,
    url varchar(100) NOT NULL,
    details varchar(255),
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE bookmark;
DROP SEQUENCE bookmark_id_seq;