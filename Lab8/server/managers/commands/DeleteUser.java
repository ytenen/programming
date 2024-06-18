package managers.commands;

import managers.DatabaseManager;
import network.Request;
import network.Response;

public class DeleteUser extends Command{

    public DeleteUser() {
        super("deleteUser", true);
    }
    @Override
    public Response execute(Request request){
        DatabaseManager databaseManager = new DatabaseManager();
        if(databaseManager.deleteUser(request.getArgs()[1])){
            return new Response("Предатель наказан");
        }
        return new Response("Предатель бежал под защиту капиталистов");
    }
}
