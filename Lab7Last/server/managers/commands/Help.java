/**
 * The {@code Help} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "help" command, providing information about available commands.
 */
package managers.commands;

import managers.CommandManager;
import network.Request;
import network.Response;
import network.User;

import java.io.Serial;
import java.io.Serializable;

public class Help extends Command implements Serializable {

    /**
     * Executes the "help" command, displaying information about available commands and their usage.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Serial
    private static final long serialVersionUID = -7522399093771810431L;
    public Help(){
        super("help", false);
    }
    @Override
    public Response execute(Request request) {
        return new Response("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n" +
                "help - вывести справку по доступным командам\n" +
                "info - вывести информацию о коллекции\n" +
                "show - вывести все элементы коллекции в строковом представлении\n" +
                "add {element} - добавить новый элемент в коллекцию\n" +
                "update id {element} - обновить значение элемента коллекции, id которого равен задванному\n" +
                "remove_by_id id - удалить элемент из коллекции по его id\n" +
                "clear - очистить коллекцию\n" +
                "execute_script file_name - считать и исполнить скрипт из указанного файла\n" +
                "exit - завершить программу\n" +
                "remove_first - удалить первый элемент из коллекции\n" +
                "add_if_max {element} - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_lower {element} - удалить из коллекции все элементы, меньшие, чем заданные\n" +
                "filter_contains_name name - вывести элементы, значение поля name которых содержит заданную строку\n" +
                "filter_starts_with_full_name fullName - вывести элементы, значение поля fullName которых начинается с заданной подстроки\n" +
                "print_field_descending_official_address - вывести значения поля officialAddress всех элементов в порядке убывания\n" +
                "register [логин] [пароль]- создать нового пользователя\n" +
                "autorization [логин] [пароль]- авторизация пользователя\n" +
                "exit_from_account - выход из текущего аккаунта\n");
    }
}
