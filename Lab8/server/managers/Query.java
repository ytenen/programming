package managers;

public class Query {

    String checkUser = "SELECT id FROM users where login = ?;";

    String getSalt = "SELECT salt from users where login = ? ;";
    String addUser = "INSERT INTO users(login, password, salt) VALUES (?, ?, ?)";

    String addOrganization = """
            INSERT INTO organization(name, x, y, creation_date, annual_turnover, full_name, type, zip_code, user_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) 
            RETURNING id;
            """;


    String deleteObject = "delete from organization where (user_id = ?) and (id = ?) returning id;";

    String updateObject = """
            update organization
            set (name, x, y, creation_date, annual_turnover, type, full_name, zip_code) = (?, ?, ?, ?, ?, ?, ?, ?) where (user_id = ?) and (id = ?)returning id;
            """;

    String goToGulag = """
            UPDATE organization
            SET user_id = 9
            WHERE user_id = ?;
            """;

    String deleteUser = """
            DELETE from users where (login) = (?) returning id;""";
    String addObjects = """
            select * from organization;
            
            """;

    String getUserObjects =  """
            select * from organization where user_id = ?;
            """;

    String getUserIdByStalin = """
            select id from users where (login = ?);
            """;
    String getUserId = """
            select id from users where (login = ?) and (password =  ?);
            """;

    String removeFirst = "remove from organization where id = 1 and user_id = ? RETURNING id;";
}
