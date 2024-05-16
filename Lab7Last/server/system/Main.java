package system;

import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import managers.RunManager;
import network.Server;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args){
        final Logger serverLogger = Logger.getLogger("logger");
        CommandManager commandManager = new CommandManager();
        CollectionManager collectionManager = new CollectionManager();
        DatabaseManager databaseManager = new DatabaseManager();
        RunManager runManager = new RunManager(commandManager);
        CollectionManager.setCollection(databaseManager.createCollection());
//        if (args.length != 0) {
//            try{
//                XMLSerializer serializer = new XMLSerializer();
//                CollectionManager.setCollection(serializer.deserialize(args[0]));
//            } catch (Exception e) {
//                serverLogger.info("Autoloading error");
//            }
//            serverLogger.info("Successfull autoloading");
//        } else {
//            serverLogger.info("File was not found");
//            System.exit(1);
//        }

        Server server = new Server( 2555, runManager, databaseManager,collectionManager);
        server.run();
    }
}
