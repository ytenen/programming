/**
 * The {@code Show} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "show" command, displaying the elements of the collection managed by
 * {@link dataManager.OrganizationManager} in ascending order of annual turnover.
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
import java.util.*;

public class Show extends Command implements Serializable {

    /**
     * Executes the "show" command, displaying the elements of the collection in ascending order of annual turnover.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = 7529620297966055266L;
    public Show(){
        super("show",false);
    }
    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        if (user.getLogin()==null || user.getPassword()==null){
            return new Response("Authorization is required to enter commands");
        }
        // Get the collection of organizations
        ArrayDeque<Organization> dec = CollectionManager.getCollection();

        // Check if the collection is empty
        if (dec.isEmpty()) {
            return new Response("Collection is empty");
        }
        // Convert the collection to an array for sorting
        Organization[] org = dec.toArray(new Organization[0]);

        // Display the names of organizations in ascending order of annual turnover
        Arrays.sort(org);
        List <Organization> list = Arrays.stream(org).sorted().toList();
        return new Response("All values: " + list);
    }
}
