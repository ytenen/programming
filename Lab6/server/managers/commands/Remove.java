/**
 * The {@code Remove} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "remove_by_id" command, removing an organization from the collection managed by
 * {@link dataManager.OrganizationManager} based on the specified ID.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Remove extends Command implements Serializable {

    /**
     * Executes the "remove_by_id" command, removing an organization from the collection based on the specified ID.
     *
     * @param args Command-line arguments, where args[1] is the ID of the organization to be removed.
     */
    @Serial
    private static final long serialVersionUID = 3954473935880079816L;
    public Remove(){
        super("remove", true);
    }
    @Override
    public Response execute(Request request) {
        // Get the collection of organizations
        ArrayDeque<Organization> dec = CollectionManager.getCollection();
        String[] args = request.getArgs();
        // Check if the command is entered correctly
        if (args.length != 2) {
            return new Response("Incorrect command, try one more time");
        } else {
            try {
                // Parse the ID from the command-line argument
                long mark = Long.parseLong(args[1]);
                var success = dec.removeIf(organization -> organization.getId()==mark);
                // Iterate through organizations and remove the one with the specified ID

                // Display appropriate messages based on the removal result
                if (!success) {
                    return new Response("Collection does not contain an element with the given id");
                } else {
                    CollectionManager.setCollection(dec);
                    return new Response("Element with id: " + mark + " deleted");
                }
            } catch (NumberFormatException e) {
                return new Response("Incorrect id value");
            }
        }
    }
}
