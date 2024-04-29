/**
 * The {@code RemoveFirst} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "remove_first" command, removing the first organization from the collection managed by
 * {@link dataManager.OrganizationManager}.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import network.Request;
import network.Response;

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
        ArrayDeque<Organization> dec = CollectionManager.getCollection();
        if (dec.isEmpty()){
            return new Response("Collection is empty(no first element)");
        }
        dec.removeFirst();
        return new Response("First element removed");

    }
}
