/**
 * The {@code ExecuteScript} class implements the {@link Command} interface to handle the execution
 * of scripts containing a sequence of commands for managing organizations.
 */
package managers.commands;


import data.Address;
import data.Coordinates;
import data.Organization;
import data.OrganizationType;
import data.generators.IdGenerator;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.Server;
import network.User;

import java.io.*;
import java.util.Map;

public class ExecuteScript extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -1085042037559644578L;

    Map<String, Command> commands;

    /**
     * Constructs an {@code ExecuteScript} object with a map of available commands.
     *
     * @param commands A map of command names to corresponding command objects.
     */
    public ExecuteScript(Map<String, Command> commands) {
        super("execute_script", true);
        this.commands = commands;
    }

    /**
     * Executes the "execute_script" command, reading and executing commands from the specified script file.
     *
     * @param  -line arguments, where args[1] is the path to the script file.
     */
    @Override
    public Response execute(Request request) {
        if (request.getArgs().length!=2){
            return new Response("Not enough info for execute script");
        }
        User user = request.getUser();
        if (user.getLogin()==null || user.getPassword()==null){
            return new Response("Authorization is required to enter commands");
        }
        String path = request.getArgs()[1];
        return executeScript(path);
    }

    /**
     * Reads and executes commands from the specified script file.
     *
     * @param filePath The path to the script file.
     */
    private Response executeScript(String filePath) {
        File file = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
            BufferedReader r = new BufferedReader(new InputStreamReader(bf));
            while (true) {
                try {
                    String line = r.readLine().trim();
                    Server.serverLogger.info(line);
                    if (!line.isEmpty()) {
                        String[] tokens = line.split(" ");
                        Command command = commands.get(tokens[0]);
                        if (command != null) {
                            // Check if the command is to execute another script
                            if (tokens[0].equals("execute_script") && tokens.length > 1) {
                                // Prevent infinite recursion by tracking already executed scripts
                                String nestedScriptPath = tokens[1];
                                if (nestedScriptPath == filePath){
                                    stringBuilder.append("Recursion!" + '\n');
                                }
                                executeScript(nestedScriptPath);
                            } else if (tokens[0].equals("add")) {
                                // Execute the add command by providing data for creating a new organization inside the script
                                addOrganization(tokens);
                                stringBuilder.append("Organization added" + '\n');
                            } else {
                                // Execute other commands normally
                                String[] data = {tokens[0]};
                                Request request = new Request(data);
                                stringBuilder.append(command.execute(request).getResult() + '\n');
                            }
                        } else {
                            System.out.println("Такой команды не существует: " + tokens[0]);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
            return new Response(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return new Response(stringBuilder.toString());
    }

    /**
     * Adds a new organization to the collection based on the arguments provided in the script.
     *
     * @param tokens The array of command arguments.
     */
    private void addOrganization(String[] tokens) {
        DatabaseManager databaseManager = new DatabaseManager();
        // Check if there are enough arguments to create an organization
        if (tokens.length >= 10) {
            // Extract data for creating a new organization from the script arguments
            String name = tokens[3];
            String fullName = tokens[4];
            int id = IdGenerator.generateId();
            String zipCode = tokens[5];
            Address address = new Address(zipCode);
            Integer coordinateX = Integer.parseInt(tokens[6]);
            Integer coordinateY = Integer.parseInt(tokens[7]);
            Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
            Integer annualTurnover = Integer.parseInt(tokens[8]);
            int orgType = Integer.parseInt(tokens[9]);
            OrganizationType type = switch (orgType) {
                case 1 -> OrganizationType.COMMERCIAL;
                case 2 -> OrganizationType.PUBLIC;
                case 3 -> OrganizationType.PRIVATE_LIMITED_COMPANY;
                case 4 -> OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                default -> throw new IllegalStateException("Unexpected value: " + orgType);
            };
            Organization organization = new Organization(id, name, coordinates, annualTurnover, fullName, type, address);
            User user = new User(tokens[1],tokens[2]);
            if (databaseManager.addOrganization(organization,user)>0){
                CollectionManager.add(organization);
                System.out.printf("Организация %s добавлена в коллекцию. Размер коллекции: %d%n", organization.getName(), CollectionManager.getCollection().size());
            }else{
                System.out.println("Add failed");
            }
        } else {
            System.out.println("Недостаточно аргументов для создания организации.");
        }
    }
}
