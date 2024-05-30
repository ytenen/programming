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



public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            BufferedReader bf = new BufferedReader(new FileReader("system/db_acces"));
            String login = bf.readLine().trim();
            String password = bf.readLine().trim();
Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:9999/studs", login, password);
            return connection;
} catch (SQLException | ClassNotFoundException e) {
            System.err.println("Ошибка подключения к базе данных");
            e.printStackTrace();
} catch (IOException e) {
            System.err.println("File not found");
}
        return null;
}

