/**
 * The {@code Info} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "info" command, providing information about the collection managed by {@link dataManager.OrganizationManager}.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import network.Request;
import network.Response;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Date;

public class Info extends Command implements Serializable {

    /**
     * Executes the "info" command, displaying information about the collection managed by.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = -4094673495798394257L;

    public Info(){
        super("info", false);
    }
    @Override
    public Response execute(Request request) {
        // Get an instance of OrganizationManager

        // Get the collection of organizations and the initialization date
        ArrayDeque<Organization> collection = CollectionManager.getCollection();
        LocalDate date = CollectionManager.getDate();

        // Display information about the collection
        return new Response("Тип коллекции: %s\n"+ collection.getClass() +
                "Дата инициализации: %s\n" + date == null ? "Коллекция не инициализирована" : date + "\n" +
                "Количество элементов: %s\n"+ collection.size());
    }
}
