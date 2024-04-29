/**
 * The {@code PrintAddress} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "print_field_descending_official_address" command, displaying the ZIP codes of organizations
 * in the collection managed by {@link dataManager.OrganizationManager} in descending order of annual turnover.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import interfaces.Command;
import organization.Organization;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class PrintAddress implements Command {

    /**
     * Executes the "print_field_descending_official_address" command, displaying the ZIP codes of organizations
     * in the collection in descending order of annual turnover.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void execute(String[] args) {
        // Get the collection of organizations
        ArrayDeque<Organization> dec = OrganizationManager.getInstance().getCollection();

        // Check if the collection is empty
        if (dec.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            // Convert the collection to an array for sorting
            Organization[] org = dec.toArray(new Organization[0]);

            // Sort the organizations based on annual turnover in descending order
            Arrays.sort(org, Comparator.comparingInt(Organization::getAnnualTurnover).reversed());

            // Display the ZIP codes of organizations in descending order of annual turnover
            for (int i = 0; i < org.length; i++) {
                System.out.println(i + 1 + ": " + org[i].getOfficialAddress().getZipCode());
            }
        }
    }
}
