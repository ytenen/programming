/**
 * The {@code StartsFullName} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "filter_starts_with_full_name" command, displaying organizations from the collection managed by
 * {@link dataManager.OrganizationManager} whose full names start with a specified substring.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import interfaces.Command;
import organization.Organization;

import java.util.ArrayDeque;

public class StartsFullName implements Command {

    /**
     * Executes the "filter_starts_with_full_name" command, displaying organizations
     * whose full names start with a specified substring.
     *
     * @param args Command-line arguments, where args[1] is the substring to filter organizations by.
     */
    @Override
    public void execute(String[] args) {
        // Get the collection of organizations
        ArrayDeque<Organization> dec = OrganizationManager.getInstance().getCollection();

        // Initialize flags and counters
        int flag = 0;
        int schetchik = 0;

        // Check if the command is entered correctly
        if (args.length != 2) {
            System.out.println("Команда введена некорректно, повторите ввод");
        } else {
            // Iterate through organizations and display those whose full names start with the specified substring
            for (Organization organization : dec) {
                if (organization.getName().startsWith(args[1])) {
                    schetchik += 1;
                    System.out.println(schetchik + ": " + organization);
                    flag = 1;
                }
            }

            // Display appropriate messages based on the filtering result
            if (flag == 0) {
                System.out.println("Коллекция не содержит подходящих элементов");
            } else {
                System.out.println("Все подходящие элементы выведены");
            }
        }
    }
}
