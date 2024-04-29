/**
 * The {@code Update} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "update id" command, updating the values of an organization in the collection managed by
 * {@link dataManager.OrganizationManager} based on the specified ID.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import input.CollectionInput;
import input.MyInputStream;
import interfaces.Command;
import organization.Organization;

import java.util.ArrayDeque;

public class Update implements Command {

    /**
     * Executes the "update id" command, updating the values of an organization based on the specified ID.
     *
     * @param args Command-line arguments, where args[1] is the ID of the organization to be updated.
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

                // Iterate through organizations and update the one with the specified ID
                for (Organization organization : dec) {
                    if (organization.getId() == mark) {
                        organization = CollectionInput.organizationInput(new MyInputStream());
                        flag = 1;
                        break;
                    }
                }

                // Display appropriate messages based on the update result
                if (flag == 0) {
                    System.out.println("Коллекция не содержит элемента с данным id");
                } else {
                    OrganizationManager.getInstance().setCollection(dec);
                    System.out.println("Элемент с id:" + mark + " успешно обновлен");
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный id");
            }
        }
    }
}
