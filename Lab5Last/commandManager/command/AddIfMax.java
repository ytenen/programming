/**
 * The {@code AddIfMax} class implements the {@link Command} interface to handle the execution
 * of adding an organization to the collection managed by {@link OrganizationManager} if its
 * annual turnover is greater than the maximum turnover in the existing collection.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import input.CollectionInput;
import input.MyInputStream;
import interfaces.Command;
import organization.Organization;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class AddIfMax implements Command {

    /**
     * Executes the "add_if_max" command, allowing the user to input information for a new
     * organization and adding it to the collection managed by {@link OrganizationManager} only if
     * its annual turnover is greater than the maximum turnover in the existing collection.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void execute(String[] args) {
        // Input organization information
        Organization organization = CollectionInput.organizationInput(new MyInputStream());

        // Get the collection of organizations
        ArrayDeque<Organization> dec = OrganizationManager.getInstance().getCollection();

        // Convert the collection to an array for sorting
        Organization[] org = dec.toArray(new Organization[0]);

        // Sort the organizations based on annual turnover in ascending order
        Arrays.sort(org, Comparator.comparingInt(Organization::getAnnualTurnover));

        // Check if the collection is empty or the new organization's turnover is greater than the current maximum
        if (org.length == 0 || organization.getAnnualTurnover() > org[org.length - 1].getAnnualTurnover()) {
            // Add the organization to the collection
            dec.add(organization);
            System.out.println("Организация успешно добавлена в коллекцию");
        } else {
            System.out.println("Значение годового оборота организации не является максимальным для коллекции");
        }
    }
}
