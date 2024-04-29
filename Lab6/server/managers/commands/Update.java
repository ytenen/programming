/**
 * The {@code Update} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "update id" command, updating the values of an organization in the collection managed by
 * {@link dataManager.OrganizationManager} based on the specified ID.
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Update extends Command implements Serializable {

    /**
     * Executes the "update id" command, updating the values of an organization based on the specified ID.
     *
     * @param args Command-line arguments, where args[1] is the ID of the organization to be updated.
     */
    @Serial
    private static final long serialVersionUID = 4100994130362065459L;
    public Update(){
        super("update",true);
    }
    @Override
    public Response execute(Request request) {
        // Get the collection of organizations
        Organization[] dec = CollectionManager.getCollection().toArray(new Organization[0]);

        String[] args = request.getArgs();
        // Check if the command is entered correctly
        if (args.length != 2) {
            return new Response("Incorrect command, try one more time");
        } else {
            try {
                // Parse the ID from the command-line argument
                long mark = Long.parseLong(args[1]);
                List list = Arrays.stream(dec).filter(organization -> organization.getId()==mark).map(organization -> request.getOrganization()).toList();
                // Iterate through organizations and update the one with the specified ID

                // Display appropriate messages based on the update result
                if (list.isEmpty()) {
                    return new Response("Collection does not contain an element with the given id");
                } else {
                    ArrayDeque<Organization> decue = new ArrayDeque<>(list);
                    CollectionManager.setCollection(decue);
                    return new Response("Element with id: " + mark + " updated successfully");
                }
            } catch (NumberFormatException e) {
                return new Response("Incorrect id");
            }
        }
    }
}
