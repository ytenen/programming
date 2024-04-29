/**
 * The {@code RemoveFirst} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "remove_first" command, removing the first organization from the collection managed by
 * {@link dataManager.OrganizationManager}.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import interfaces.Command;

public class RemoveFirst implements Command {

    /**
     * Executes the "remove_first" command, removing the first organization from the collection.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void execute(String[] args) {
        OrganizationManager.getInstance().getCollection().removeFirst();
    }
}
