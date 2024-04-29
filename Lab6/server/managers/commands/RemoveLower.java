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
import network.Request;
import network.Response;

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
        // Get the collection of organizations
        ArrayDeque<Organization> dec = CollectionManager.getCollection();

        // Input information for the organization to compare
        Organization organization = request.getOrganization();

        // Create a new collection for the filtered organizations
        ArrayDeque<Organization> finalDec = new ArrayDeque<>();

        // Iterate through organizations and add those with higher annual turnover to the new collection
        for (Organization org : dec) {
            if (org.getAnnualTurnover() > organization.getAnnualTurnover()) {
                finalDec.add(org);
            }
        }

        // Update the collection with the filtered organizations
        CollectionManager.setCollection(finalDec);
        return new Response("Collection changed succsesfully");
    }
}
