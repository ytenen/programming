/**
 * The {@code RemoveLower} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "remove_lower" command, removing organizations from the collection managed by
 * {@link dataManager.OrganizationManager} with annual turnover lower than the specified organization.
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

public class RemoveLower extends Command implements Serializable {

    /**
     * Executes the "remove_lower" command, removing organizations from the collection
     * with annual turnover lower than the specified organization.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = -8014900859486434686L;
    public RemoveLower(){
        super("remove_lower",false);
    }
    @Override
    public Response execute(Request request) {
        User user = request.getUser();

        // Get the collection of organizations
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayDeque<Organization> dec = CollectionManager.getCollection();

        // Input information for the organization to compare
        Organization organization = request.getOrganization();

        // Create a new collection for the filtered organizations
        ArrayDeque<Organization> finalDec = new ArrayDeque<>();

        // Iterate through organizations and add those with higher annual turnover to the new collection
        for (Organization org : dec) {
            if (org.getAnnualTurnover() < organization.getAnnualTurnover()) {
                databaseManager.deleteObject(org.getId(),user);
                CollectionManager.removeById(org.getId());
            }
        }

        // Update the collection with the filtered organizations
        return new Response("Collection changed succsesfully");
    }
}
