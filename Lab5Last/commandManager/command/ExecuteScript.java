/**
 * The {@code ExecuteScript} class implements the {@link Command} interface to handle the execution
 * of scripts containing a sequence of commands for managing organizations.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import dataManager.idStorage;
import interfaces.Command;
import organization.Address;
import organization.Coordinates;
import organization.Organization;
import organization.OrganizationType;

import java.io.*;
import java.util.Map;

public class ExecuteScript implements Command {

    Map<String, Command> commands;

    /**
     * Constructs an {@code ExecuteScript} object with a map of available commands.
     *
     * @param commands A map of command names to corresponding command objects.
     */
    public ExecuteScript(Map<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Executes the "execute_script" command, reading and executing commands from the specified script file.
     *
     * @param args Command-line arguments, where args[1] is the path to the script file.
     */
    @Override
    public void execute(String[] args) {
        executeScript(args[1]);
    }

    /**
     * Reads and executes commands from the specified script file.
     *
     * @param filePath The path to the script file.
     */
    private void executeScript(String filePath) {
        File file = new File(filePath);
        try {
            BufferedInputStream bf = new BufferedInputStream(new FileInputStream(file));
            BufferedReader r = new BufferedReader(new InputStreamReader(bf));
            while (true) {
                try {
                    String line = r.readLine().trim();
                    if (!line.isEmpty()) {
                        String[] tokens = line.split(" ");
                        Command command = commands.get(tokens[0]);
                        if (command != null) {
                            // Check if the command is to execute another script
                            if (tokens[0].equals("execute_script") && tokens.length > 1) {
                                // Prevent infinite recursion by tracking already executed scripts
                                String nestedScriptPath = tokens[1];
                                executeScript(nestedScriptPath);
                            } else if (tokens[0].equals("add")) {
                                // Execute the add command by providing data for creating a new organization inside the script
                                addOrganization(tokens);
                            } else {
                                // Execute other commands normally
                                command.execute(tokens);
                            }
                        } else {
                            System.out.println("Такой команды не существует: " + tokens[0]);
                        }
                    }
                } catch (IOException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
}
    }

    /**
     * Adds a new organization to the collection based on the arguments provided in the script.
     *
     * @param tokens The array of command arguments.
     */
    private void addOrganization(String[] tokens) {
        OrganizationManager organizationManager = OrganizationManager.getInstance();
        // Check if there are enough arguments to create an organization
        if (tokens.length >= 8) {
            // Extract data for creating a new organization from the script arguments
            String name = tokens[1];
            String fullName = tokens[2];
            long id = idStorage.getId();
            String zipCode = tokens[3];
            Address address = new Address(zipCode);
            Integer coordinateX = Integer.parseInt(tokens[4]);
            Integer coordinateY = Integer.parseInt(tokens[5]);
            Coordinates coordinates = new Coordinates(coordinateX, coordinateY);
            Integer annualTurnover = Integer.parseInt(tokens[6]);
            int orgType = Integer.parseInt(tokens[7]);
            OrganizationType type = switch (orgType) {
                case 1 -> OrganizationType.COMMERCIAL;
                case 2 -> OrganizationType.PUBLIC;
                case 3 -> OrganizationType.PRIVATE_LIMITED_COMPANY;
                case 4 -> OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                default -> throw new IllegalStateException("Unexpected value: " + orgType);
            };

            Organization organization = new Organization(name, fullName, id, coordinates, address, annualTurnover, type);
            organizationManager.addElement(organization);
            System.out.printf("Организация %s добавлена в коллекцию. Размер коллекции: %d%n", organization.getName(), organizationManager.getCollection().size());
        } else {
            System.out.println("Недостаточно аргументов для создания организации.");
        }
    }
}
