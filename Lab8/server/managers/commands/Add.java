/**
 * The {@code Add} class implements the {@link Command} interface to handle the execution
 * of adding an organization to the collection managed by {@link OrganizationManager}.
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

import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

public class Add extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -348548537650743828L;

    public Add(){
        super("add",false);
    }

    /**
     * Executes the "add" command, allowing the user to input information for a new
     * organization and adding it to the collection managed.
     *
     *  Command-line arguments (not used in this implementation).
     */

    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DatabaseManager databaseManager = new DatabaseManager();
        Organization organization = request.getOrganization();
        if (databaseManager.addOrganization(organization,user)!=-1){
            CollectionManager.setCollection(databaseManager.createCollection());
            return new Response("Organization added successfully");
        }
        return new Response("Add failed, try one more time");
    }
}
