/**
 * The {@code StartsFullName} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "filter_starts_with_full_name" command, displaying organizations from the collection managed by
 * {@link dataManager.OrganizationManager} whose full names start with a specified substring.
 */
package managers.commands;



import data.Organization;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartsFullName extends Command implements Serializable {

    /**
     * Executes the "filter_starts_with_full_name" command, displaying organizations
     * whose full names start with a specified substring.
     *
     * @param args Command-line arguments, where args[1] is the substring to filter organizations by.
     */
    @Serial
    private static final long serialVersionUID = 5707772232706484829L;
    public StartsFullName(){
        super("filter_starts_with_full_name",true);
    }
    @Override
    public Response execute(Request request) {
        // Get the collection of organizations
        Organization[] dec = CollectionManager.getCollection().toArray(new Organization[0]);

        String[] args = request.getArgs();
        // Check if the command is entered correctly
        if (args.length != 2) {
            return new Response("Incorrect command, try one more time");
        }
        // Iterate through organizations and display those whose full names start with the specified substring
        List list = Arrays.stream(dec).filter(organization -> organization.getName().startsWith(args[1])).toList();

        // Display appropriate messages based on the filtering result
        if (list.isEmpty()) {
            return new Response("Collection does not contain matching elements");
        }
        return new Response("All suitable values: " + list);
    }
}
