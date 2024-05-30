/**
 * The {@code Exit} class implements the {@link Command} interface to handle the execution
 * of the "exit" command, terminating the application.
 */
package managers.commands;


import managers.CollectionManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;

public class Exit extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = -4035405092428178978L;
    /**
     * Executes the "exit" command, terminating the application with a status code of 0.
     *
     *  Command-line arguments (not used in this implementation).
     */
    public Exit(){
        super("exit", false);
    }
    @Override
    public Response execute(Request request) {
        System.exit(0);
        return new Response("Exit confirmed");
    }
}
