/**
 * The {@code RemoveFirst} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "remove_first" command, removing the first organization from the collection managed by
 * {@link dataManager.OrganizationManager}.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serializable;
import java.util.ArrayDeque;

public class RemoveFirst extends Command implements Serializable {

    /**
     * Executes the "remove_first" command, removing the first organization from the collection.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    private static final long serialVersionUID = 7079425780716508875L;
    public RemoveFirst(){
        super("remove_first", false);
    }
    @Override
    public Response execute(Request request) {
        User user = request.getUser();
        DatabaseManager databaseManager = new DatabaseManager();
        ArrayDeque<Organization> dec = CollectionManager.getCollection();
        if (databaseManager.removeFirst(user)==1){
            dec.removeFirst();
            CollectionManager.setCollection(dec);
            return new Response("First element removed");
        } else if (databaseManager.removeFirst(user)==0) {
            return new Response("Collection is empty(no first element)");
        }
        else {
            return new Response("Error while deleting first element");
        }

    }
}
