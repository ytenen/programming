package managers.commands;

import managers.DatabaseManager;
import network.Request;
import network.Response;

public class goToGulag extends Command{
    public goToGulag() {
        super("goToGulag", true);
    }

    @Override
    public Response execute(Request request){
        DatabaseManager databaseManager = new DatabaseManager();
        if(databaseManager.goToGulag(request.getArgs()[1])){
            return new Response("Национализация прошла успешно");
        }
        return new Response("Предатель скрылся, этим займется НКВД(или у него ничего нет(статья 209.1 - тунеядство))");
    }
}
