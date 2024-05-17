/**
 * The {@code Remove} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "remove_by_id" command, removing an organization from the collection managed by
 * {@link dataManager.OrganizationManager} based on the specified ID.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.Server;
import network.User;

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
        if (request.getArgs().length<2){
            return new Response("Not enough info for remove organization, maybe you forgot login or password");
        }
        // Get the collection of organizations
        User user = request.getUser();
        String[] args = request.getArgs();
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayDeque<Organization> dec =CollectionManager.getCollection();
        // Check if the command is entered correctly
        try {
            // Parse the ID from the command-line argument
            int id = Integer.parseInt(args[1]);
            // Display appropriate messages based on the removal result
            if (!databaseManager.deleteObject(id, user)) {
                return new Response("Cant delete object");
            } else {
                dec.removeIf(organization -> organization.getId()==id);
                CollectionManager.setCollection(dec);
                return new Response("Element with id: " + id + " deleted");
            }
        } catch (NumberFormatException e) {
            return new Response("Incorrect id value");
        }
    }
}
