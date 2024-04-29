/**
 * The {@code Save} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "save" command, saving the current state of the collection managed by
 * {@link dataManager.OrganizationManager} to an XML file.
 */
package commandManager.command;

import dataManager.OrganizationManager;
import interfaces.Command;
import organization.Organization;
import system.XMLSerializer;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayDeque;

public class Save implements Command {

    /**
     * Executes the "save" command, saving the current state of the collection to an XML file.
     *
     * @param args Command-line arguments, where args[1] is the path to the XML file.
     */
    @Override
    public void execute(String[] args) {
        // Get the path to the XML file
        String path = args[1];

        // Get the array of organizations from the collection
        ArrayDeque<Organization> collection = OrganizationManager.getInstance().getCollection();

        // Create an XStream instance with a DOM driver
        XMLSerializer stream = new XMLSerializer();

        // Convert the collection to XML format
        String xml = stream.serialize(collection);

        try {
            // Write the XML data to the specified file
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(path));
            byte[] bytes = xml.getBytes();
            output.write(bytes);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
