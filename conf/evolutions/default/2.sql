# Bookmark schema

# --- !Ups

CREATE TABLE bookmark (
    id integer NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    url varchar(100) NOT NULL,
    details varchar(255),
    categoryId integer NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(categoryId) REFERENCES category(id)
);

# --- !Downs

DROP TABLE bookmark;
