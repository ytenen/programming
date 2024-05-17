package managers;

import data.Address;
import data.Coordinates;
import data.Organization;
import data.OrganizationType;
import exeptions.UserExistsException;
import network.Request;
import network.Server;
import network.User;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseManager {
    private final Query queryManager = new Query();


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



    public void addUser(User user) throws UserExistsException {
        Connection connection = connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.checkUser);
            preparedStatement.setString(1,user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                throw new UserExistsException();
            }
            Hasher passwordHasherManager = new Hasher();
            String salt = saltGenerator();
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement pr = connection.prepareStatement(queryManager.addUser);
            pr.setString(1, user.getLogin());
            pr.setString(2, password);
            pr.setString(3, salt);
            pr.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ошибка при выполнении запроса");
        }
    }


    public int addOrganization(Organization organization, User user) {
        Connection connection = connect();
        int user_id = 0;
        try {
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return 0;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.addOrganization);
            preparedStatement.setString(1, organization.getName());
            preparedStatement.setDouble(2, organization.getCoordinates().getX());
            preparedStatement.setInt(3, organization.getCoordinates().getY());
            preparedStatement.setString(4, organization.getCreationDate().toString());
            preparedStatement.setInt(5, organization.getAnnualTurnover());
            preparedStatement.setString(6, organization.getFullName());
            preparedStatement.setString(7, organization.getType().toString());
            preparedStatement.setString(8, organization.getOfficialAddress().getZipCode());
            preparedStatement.setInt(9, user_id);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.err.println("Не удалось добавить объект");
                return -1;
            }
            System.err.println("Объект был успешно добавлен");
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при выполнении запроса");
            return -1;

        }


    }

    public int removeFirst(User user){
        Connection connection = connect();
        int user_id = 0;
        try{
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return 0;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.removeFirst);
            preparedStatement.setInt(1,user_id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return 1;
            }
            return 0;
        } catch (SQLException e) {
            return -1;
        }
    }



    public boolean deleteObject(int id, User user) {
        Connection connection = connect();
        int user_id = 0;
        try {
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.deleteObject);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setLong(2, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean existUser(User user){
        Connection connection = connect();
        try{
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            System.out.println("SQL error");
        }
        return false;
    }
    public boolean updateObject(int id, User user, Organization organization) {
        Connection connection = connect();
        int user_id = 0;
        try {
            Hasher passwordHasherManager = new Hasher();
            PreparedStatement getSalt = connection.prepareStatement(queryManager.getSalt);
            getSalt.setString(1,user.getLogin());
            ResultSet getSaltResult = getSalt.executeQuery();
            if (!getSaltResult.next()){
                return false;
            }
            String salt = getSaltResult.getString(1);
            String password = passwordHasherManager.hashPassword(user.getPassword() + salt );
            PreparedStatement preparedStatement = connection.prepareStatement(queryManager.getUserId);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user_id = resultSet.getInt(1);
            }
            preparedStatement = connection.prepareStatement(queryManager.updateObject);
            preparedStatement.setString(1, organization.getName());
            preparedStatement.setLong(2, organization.getCoordinates().getY());
            preparedStatement.setDouble(3, organization.getCoordinates().getX());
            preparedStatement.setString(4, organization.getCreationDate().toString());
            preparedStatement.setDouble(5, organization.getAnnualTurnover());
            preparedStatement.setString(6, organization.getType().toString());
            preparedStatement.setString(7, organization.getFullName());
            preparedStatement.setString(8, organization.getOfficialAddress().getZipCode());
            preparedStatement.setInt(9, user_id);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayDeque<Organization> createCollection() {
        Connection connection = connect();
        ArrayDeque<Organization> organizations = new ArrayDeque<>();
        try {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(queryManager.addObjects);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    organizations.add(new Organization(resultSet.getInt(1), resultSet.getString(2), new Coordinates(resultSet.getDouble(3),
                            resultSet.getInt(4)),
                            resultSet.getString(5), resultSet.getInt(6),
                            resultSet.getString(7),
                            OrganizationType.valueOf(resultSet.getString(8)),
                            new Address(resultSet.getString(9))));
                }
                return organizations;


            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Ошибка выполнения запроса");
                return new ArrayDeque<>();
            }


        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.err.println("Поля объектов не валидны");
            return new ArrayDeque<>();
        }
    }


    private String saltGenerator() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 15; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
