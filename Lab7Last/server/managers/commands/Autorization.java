package managers.commands;


import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;


public class Autorization extends Command{
    public Autorization() {
        super("autorization", true);
    }

    @Override
    public Response execute(Request request) {

        DatabaseManager databaseManager = new DatabaseManager();
        User user = request.getUser();
        if (databaseManager.existUser(user)){
            CommandManager.setLogin(request.getArgs()[1]);
            CommandManager.setPassword(request.getArgs()[2]);
            return new Response("Successfull autorization!");
        }
        return new Response("User with this login and password does not exist");
    }
}
