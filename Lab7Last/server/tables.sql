CREATE TABLE IF NOT EXISTS users(
    id serial PRIMARY KEY ,
    login text NOT NULL UNIQUE ,
    password text NOT NULL ,
    salt text NOT NULL
);
CREATE TABLE IF NOT EXISTS organization(
    id serial PRIMARY KEY ,
    name text NOT NULL ,
    x double precision CHECK ( x < 265 ),
    y int NOT NULL ,
    creation_date date NOT NULL,
    annual_turnover int CHECK ( annual_turnover>0 ) NOT NULL ,
    full_name varchar(1552) NOT NULL ,
    type text NOT NULL ,
    zip_code text NOT NULL ,
    user_id int NOT NULL REFERENCES users(id)
);

