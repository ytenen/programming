/**
 * The {@code AddIfMax} class implements the {@link Command} interface to handle the execution
 * of adding an organization to the collection managed by {@link OrganizationManager} if its
 * annual turnover is greater than the maximum turnover in the existing collection.
 */
package managers.commands;


import data.Organization;
import data.generators.IdGenerator;
import data.generators.OrganizationGenerator;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

public class AddIfMax extends Command implements Serializable {

    /**
     * Executes the "add_if_max" command, allowing the user to input information for a new
     * organization and adding it to the collection managed only if
     * its annual turnover is greater than the maximum turnover in the existing collection.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = 536612929025814542L;

    public AddIfMax(){
        super("add_if_max", false);
    }
    @Override
    public Response execute(Request request) {
        // Input organization information
        DatabaseManager databaseManager = new DatabaseManager();
        Organization organization = request.getOrganization();

        // Get the collection of organizations
        ArrayDeque<Organization> dec = CollectionManager.getCollection();

        // Convert the collection to an array for sorting
        Organization[] org = dec.toArray(new Organization[0]);

        // Sort the organizations based on annual turnover in ascending order
        Arrays.sort(org, Comparator.comparingInt(Organization::getAnnualTurnover));

        // Check if the collection is empty or the new organization's turnover is greater than the current maximum
        if (org.length == 0 || organization.getAnnualTurnover() > org[org.length - 1].getAnnualTurnover()) {
            // Add the organization to the collection
            if (databaseManager.addOrganization(organization,request.getUser())==1){
                CollectionManager.setCollection(databaseManager.createCollection());
                dec.add(organization);
            }
            return new Response("The organization has been successfully added to the collection");
        }
        return new Response("The value of the annual turnover of the organization is not the maximum for the collection");
    }
}
