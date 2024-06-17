package network;

import data.Organization;
import data.generators.IdGenerator;
import data.generators.OrganizationGenerator;

import java.util.Scanner;

public class Program {
    private static final int port = 2555;
    private static final int reconnectionTimeout = 5000;
    private static final int maxReconnectionAttempts = 5;
    public static User user = new User(null,null);
    public void execute() throws InterruptedException {
        Client client = new Client("localhost", port, reconnectionTimeout, maxReconnectionAttempts);
        RegistrationPanel registrationPanel = new RegistrationPanel(client);
        //При отправке запросов проверять только соответсвие типов вводимых данных типам ожидаемых данных
        //все команды проверять на сервере + все команды перенести на сервер, в клиенте должны быть классы reqest, response, client, program, data classes
    }
}


