/**
 * The {@code ContainsName} class implements the {@link Command} interface to handle the execution
 * of searching for organizations in the collection managed by {@link OrganizationManager} based on
 * the partial match of their names.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import managers.CommandManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContainsName extends Command implements Serializable {

    /**
     * Executes the "contains_name" command, searching for organizations in the collection based on
     * the partial match of their names and displaying the matching elements.
     *
     * @param args Command-line arguments, where args[1] is the partial name to search for.
     */
    @Serial
    private static final long serialVersionUID = -3170195748080062665L;

    public ContainsName(){
        super("filter_contains_name", true);
    }
    @Override
    public Response execute(Request request) {
        // Get the collection of organizations
        Organization[] dec = CollectionManager.getCollection().toArray(new Organization[0]);
        String[] args =request.getArgs();
        // Check if the command is entered correctly
        if (args.length != 2) {
            return new Response("Incorrect command, try one more time");
        }
        // Iterate through organizations and check for partial name match
        List list = Arrays.stream(dec).filter(organization -> organization.getName().contains(args[1])).toList();
        // Display appropriate messages based on search results
        if (list.isEmpty()) {
            return new Response("The collection does not contain any matching items");
        } else {
            return new Response("All suitable elements are displayed: " + list);
        }
    }
}
