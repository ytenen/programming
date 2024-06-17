package managers.commands;


import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;


public class Authorization extends Command{
    public Authorization() {
        super("autorization", false);
    }

    @Override
    public Response execute(Request request) {

        DatabaseManager databaseManager = new DatabaseManager();
        User user = request.getUser();
        if (databaseManager.existUser(user)){
            return new Response("Successfull authorization!");
        }
        return new Response("This login not exist");
    }
}
