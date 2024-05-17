/**
 * The {@code Clear} class implements the {@link Command} interface to handle the execution
 * of clearing the collection of organizations managed by {@link OrganizationManager}.
 */
package managers.commands;


import data.Coordinates;
import data.Organization;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serializable;
import java.util.ArrayDeque;

public class Clear extends Command implements Serializable {
    private static final long serialVersionUID = -6487974771420345627L;

    public Clear(){
        super("clear", false);
    }

    /**
     * Executes the "clear" command, removing all organizations from the collection
     * managed.
     *
     * @param request Command-line arguments (not used in this implementation).
     */
    @Override
    public Response execute(Request request) {
        // Clear the collection of organizations
        User user = request.getUser();
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayDeque<Organization> collection =CollectionManager.getCollection();
        for (Organization organization : collection){
            if(databaseManager.deleteObject(organization.getId(),user)){
                CollectionManager.removeById(organization.getId());
            }
        }
        return new Response("Collection was cleared");

    }
}
