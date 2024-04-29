/**
 * The {@code Clear} class implements the {@link Command} interface to handle the execution
 * of clearing the collection of organizations managed by {@link OrganizationManager}.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import interfaces.Command;

public class Clear implements Command {

    /**
     * Executes the "clear" command, removing all organizations from the collection
     * managed by {@link OrganizationManager}.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void execute(String[] args) {
        // Clear the collection of organizations
        OrganizationManager.getInstance().getCollection().clear();
    }
}
