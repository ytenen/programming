/**
 * The {@code Save} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "save" command, saving the current state of the collection managed by
 * {@link dataManager.OrganizationManager} to an XML file.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import network.Request;
import network.Response;
import system.XMLSerializer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;

public class Save extends Command implements Serializable {

    /**
     * Executes the "save" command, saving the current state of the collection to an XML file.
     *
     * @param args Command-line arguments, where args[1] is the path to the XML file.
     */
    @Serial
    private static final long serialVersionUID = 4289368190535032241L;
    public Save(){
        super("save",true);
    }
    @Override
    public Response execute(Request request) {
        String[] args = request.getArgs();
        return CollectionManager.save(args);
    }
}
