/**
 * The {@code Exit} class implements the {@link Command} interface to handle the execution
 * of the "exit" command, terminating the application.
 */
package commandManager.command;

import interfaces.Command;

public class Exit implements Command {

    /**
     * Executes the "exit" command, terminating the application with a status code of 0.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void execute(String[] args) {
        System.exit(0);
    }
}
