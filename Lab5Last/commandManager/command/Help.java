/**
 * The {@code Help} class implements the {@link interfaces.Command} interface to handle the execution
 * of the "help" command, providing information about available commands.
 */
package commandManager.command;

import interfaces.Command;

public class Help implements Command {

    /**
     * Executes the "help" command, displaying information about available commands and their usage.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    @Override
    public void execute(String[] args) {
        System.out.println('\n' + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("help - вывести справку по доступным командам");
        System.out.println("info - вывести информацию о коллекции");
        System.out.println("show - вывести все элементы коллекции в строковом представлении");
        System.out.println("add {element} - добавить новый элемент в коллекцию");
        System.out.println("update id {element} - обновить значение элемента коллекции, id которого равен задванному");
        System.out.println("remove_by_id id - удалить элемент из коллекции по его id");
        System.out.println("clear - очистить коллекцию");
        System.out.println("save - сохранить коллекцию в файл");
        System.out.println("execute_script file_name - считать и исполнить скрипт из указанного файла");
        System.out.println("exit - завершить программу");
        System.out.println("remove_first - удалить первый элемент из коллекции");
        System.out.println("add_if_max {element} - добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        System.out.println("remove_lower {element} - удалить из коллекции все элементы, меньшие, чем заданные");
        System.out.println("filter_contains_name name - вывести элементы, значение поля name которых содержит заданную строку");
        System.out.println("filter_starts_with_full_name fullName - вывести элементы, значение поля fullName которых начинается с заданной подстроки");
        System.out.println("print_field_descending_official_address - вывести значения поля officialAddress всех элементов в порядке убывания");
        System.out.println();
    }
}
