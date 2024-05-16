/**
 * The {@code PrintAddress} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "print_field_descending_official_address" command, displaying the ZIP codes of organizations
 * in the collection managed by {@link dataManager.OrganizationManager} in descending order of annual turnover.
 */
package managers.commands;

import data.Organization;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PrintAddress extends Command implements Serializable {

    /**
     * Executes the "print_field_descending_official_address" command, displaying the ZIP codes of organizations
     * in the collection in descending order of annual turnover.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = -2152635904824633563L;
    public PrintAddress(){
        super("print_field_descending_official_address",false);
    }
    @Override
    public Response execute(Request request) {
        // Get the collection of organizations
        User user = request.getUser();
        if (user.getLogin()==null || user.getPassword()==null){
            return new Response("Authorization is required to enter commands");
        }
        ArrayDeque<Organization> dec = CollectionManager.getCollection();

        // Check if the collection is empty
        if (dec.isEmpty()) {
            return new Response("Collection is empty");
        }
        // Convert the collection to an array for sorting
        Organization[] org = dec.toArray(new Organization[0]);

        // Sort the organizations based on annual turnover in descending order
        Arrays.sort(org, Comparator.comparingInt(Organization::getAnnualTurnover).reversed());

        // Display the ZIP codes of organizations in descending order of annual turnover
        List  list = Arrays.stream(org).map(organization -> organization.getOfficialAddress().getZipCode()).toList();
        return new Response("All suitable address values: " + list);

    }
}
