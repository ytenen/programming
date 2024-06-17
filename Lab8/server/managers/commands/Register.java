package managers.commands;

import exeptions.UserExistsException;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;


public class Register extends Command implements Serializable {

    @Serial
    private static final long serialVersionUID = -2264026831201018523L;

    public Register() {
        super("register", false);
    }

    @Override
    public Response execute(Request request){
        User user = request.getUser();
        DatabaseManager databaseManager = new DatabaseManager();
        try{
            databaseManager.addUser(user);
            return new Response("User added successfully");
        }catch (UserExistsException e){
            return new Response(e.getMessage());
        }
        catch (Exception e){
            return new Response("Error while adding user, try another login");
        }

    }
}
