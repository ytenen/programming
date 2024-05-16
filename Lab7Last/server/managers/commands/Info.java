/**
 * The {@code Info} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "info" command, providing information about the collection managed by {@link dataManager.OrganizationManager}.
 */
package managers.commands;


import data.Organization;
import managers.CollectionManager;
import managers.CommandManager;
import managers.DatabaseManager;
import network.Request;
import network.Response;
import network.User;

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
        User user = request.getUser();
        if (user.getLogin()==null || user.getPassword()==null){
            return new Response("Authorization is required to enter commands");
        }
        // Get the collection of organizations and the initialization date
        ArrayDeque<Organization> collection = CollectionManager.getCollection();
        LocalDate date = CollectionManager.getDate();

        // Display information about the collection
        return new Response("Тип коллекции: "+ collection.getClass() +"\n"+
                "Дата инициализации: " + date == null ? "Коллекция не инициализирована" : date + "\n" +
                "Количество элементов: "+ collection.size());
    }
}
