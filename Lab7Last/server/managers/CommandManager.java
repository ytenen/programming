package managers;

import exeptions.UnknowCommandException;
import managers.commands.*;
import network.Request;
import network.Response;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private static Map<String, Command> commands;

    public CommandManager(){
        commands = new HashMap<>();
        commands.put("add", new Add());
        commands.put("help", new Help());
        commands.put("show", new Show());
        commands.put("clear", new Clear());
        commands.put("update", new Update());
        commands.put("remove_by_id", new Remove());
        commands.put("remove_first", new RemoveFirst());
        commands.put("filter_contains_name", new ContainsName());
        commands.put("filter_starts_with_full_name", new StartsFullName());
        commands.put("add_if_max", new AddIfMax());
        commands.put("print_field_descending_official_address", new PrintAddress());
        commands.put("remove_lower", new RemoveLower());
        commands.put("execute_script", new ExecuteScript(commands));
        commands.put("info", new Info());
        commands.put("register", new Register());
        commands.put("autorization", new Autorization());
        commands.put("give_login", new GiveLogin());
    }

    public static Response startExecuting(Request request)  {
        Command command = commands.get(request.getArgs()[0]);
        if (command== null){
            return new Response("No command with this name");
        }
        else {
            if (command.isHasArguments() && request.getArgs().length<2){
                return new Response("No arguments for command: " + command.getName());
            }
            return command.execute(request);
        }
    }

    public Map<String, Command> getCommands(){
        return commands;
    }
}
