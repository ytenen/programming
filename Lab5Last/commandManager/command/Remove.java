/**
 * The {@code Remove} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "remove_by_id" command, removing an organization from the collection managed by
 * {@link dataManager.OrganizationManager} based on the specified ID.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import interfaces.Command;
import organization.Organization;

import java.util.ArrayDeque;

public class Remove implements Command {

    /**
     * Executes the "remove_by_id" command, removing an organization from the collection based on the specified ID.
     *
     * @param args Command-line arguments, where args[1] is the ID of the organization to be removed.
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
            try {
                // Parse the ID from the command-line argument
                long mark = Long.parseLong(args[1]);

                // Iterate through organizations and remove the one with the specified ID
                for (Organization organization : dec) {
                    if (organization.getId() == mark) {
                        dec.remove(organization);
                        flag = 1;
                        break;
                    }
                }

                // Display appropriate messages based on the removal result
                if (flag == 0) {
                    System.out.println("Коллекция не содержит элемента с данным id");
                } else {
                    OrganizationManager.getInstance().setCollection(dec);
                    System.out.println("Элемент с id:" + mark + " удален");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный id");
            }
        }
    }
}
