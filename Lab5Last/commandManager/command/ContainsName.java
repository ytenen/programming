/**
 * The {@code ContainsName} class implements the {@link Command} interface to handle the execution
 * of searching for organizations in the collection managed by {@link OrganizationManager} based on
 * the partial match of their names.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import interfaces.Command;
import organization.Organization;

import java.util.ArrayDeque;

public class ContainsName implements Command {

    /**
     * Executes the "contains_name" command, searching for organizations in the collection based on
     * the partial match of their names and displaying the matching elements.
     *
     * @param args Command-line arguments, where args[1] is the partial name to search for.
     */
    @Override
    public void execute(String[] args) {
        // Get the collection of organizations
        ArrayDeque<Organization> dec = OrganizationManager.getInstance().getCollection();
        int flag = 0;

        // Check if the command is entered correctly
        if (args.length != 2) {
            System.out.println("Команда введена некорректно, повторите ввод");
        } else {
            int counter = 0;

            // Iterate through organizations and check for partial name match
            for (Organization organization : dec) {
                if (organization.getName().contains(args[1])) {
                    counter += 1;
                    System.out.println(counter + ": " + organization);
                    flag = 1;
                }
            }

            // Display appropriate messages based on search results
            if (flag == 0) {
                System.out.println("Коллекция не содержит подходящих элементов");
            } else {
                System.out.println("Все подходящие элементы выведены");
            }
        }
    }
}
