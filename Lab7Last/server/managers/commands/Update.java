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
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

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
        if (request.getArgs().length<2){
            return new Response("Not enough info for update organization, maybe you forgot login or password");
        }
        User user = request.getUser();
        if (user.getLogin()==null || user.getPassword()==null){
            return new Response("Authorization is required to enter commands");
        }
        // Get the collection of organizations
        DatabaseManager databaseManager = new DatabaseManager();
        Organization[] dec = CollectionManager.getCollection().toArray(new Organization[0]);

        String[] args = request.getArgs();
        // Check if the command is entered correctly
        try {
            // Parse the ID from the command-line argument
            int mark = Integer.parseInt(args[1]);
            databaseManager.updateObject(mark,user,request.getOrganization());
            List list = Arrays.stream(dec).filter(organization -> organization.getId()==mark).map(organization -> request.getOrganization()).toList();
            // Iterate through organizations and update the one with the specified ID

            // Display appropriate messages based on the update result
            if (list.isEmpty()) {
                return new Response("Collection does not contain an element with the given id");
            } else {
                CollectionManager.setCollection(databaseManager.createCollection());
                return new Response("Element with id: " + mark + " updated successfully");
            }
        } catch (NumberFormatException e) {
            return new Response("Incorrect id");
        }
    }
}
