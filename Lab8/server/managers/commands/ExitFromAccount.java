package managers.commands;

import network.Request;
import network.Response;

public class ExitFromAccount extends Command{
    public ExitFromAccount() {
        super("exit_from_account", false);
    }

    @Override
    public Response execute(Request request){
        return new Response("Exit confirmed");
    }
}
