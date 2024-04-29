/**
 * The {@code Add} class implements the {@link Command} interface to handle the execution
 * of adding an organization to the collection managed by {@link OrganizationManager}.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import input.CollectionInput;
import input.MyInputStream;
import interfaces.Command;
import organization.Organization;

public class Add implements Command {

    /**
     * Executes the "add" command, allowing the user to input information for a new
     * organization and adding it to the collection managed by {@link OrganizationManager}.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void execute(String[] args) {
        // Get an instance of OrganizationManager
        OrganizationManager om = OrganizationManager.getInstance();

        // Create an instance of MyInputStream
        MyInputStream scanner = new MyInputStream();

        // Use CollectionInput to get input for creating an Organization
        Organization organization = CollectionInput.organizationInput(scanner);

        // Add the created organization to the OrganizationManager
        om.addElement(organization);

        // Print a message indicating that the organization has been added to the collection
        System.out.println("Организация добавлена в коллекцию");
    }
}
