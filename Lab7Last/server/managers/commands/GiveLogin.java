package managers.commands;

import managers.CommandManager;
import network.Request;
import network.Response;
import network.User;

public class GiveLogin extends Command{

    public GiveLogin() {
        super("give_login", false);
    }
    @Override
    public Response execute(Request request){
        User user = request.getUser();
        if (user.getLogin()==null || user.getPassword()==null){
            return new Response("Authorization is required to enter commands");
        }
        return new Response(CommandManager.getLogin());
    }
}
