CREATE TABLE robots (
    id int NOT NULL,
    name varchar(256) NOT NULL,
    username varchar(25) NOT NULL,
    email text NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT lower_cased_email CHECK (email = lower(email))
);
