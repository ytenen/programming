/**
 * The {@code Command} interface defines the contract for command implementations.
 * Implementing classes should provide logic for executing a command based on the specified arguments.
 */
package interfaces;

public interface Command {

    /**
     * Executes the command with the given arguments.
     *
     * @param args The array of arguments passed to the command.
     */
    void execute(String[] args);
}
