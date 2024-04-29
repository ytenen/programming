/**
 * The {@code NameInput} class provides methods to input and validate organization names.
 * It includes methods for both short names and full names, ensuring they are not empty strings.
 */
package input;

public class NameInput {

    /**
     * Prompts the user to input a short name for the organization, validating that it is not an empty string.
     *
     * @param bf The input stream to read user input.
     * @return The inputted short name.
     */
    public static String nameInput(MyInputStream bf) {
        while (true) {
            System.out.print("Введите название организации: ");
            String name = MyInputStream.Scan();

            // Validate that the name is not an empty string
            if (name.replaceAll(" ", "").isEmpty()) {
                System.out.println("Вы ввели пустую строку, она не может быть названием организации!");
            } else {
                return name;
            }
        }
    }

    /**
     * Prompts the user to input a full name for the organization, validating that it is not an empty string.
     *
     * @param bf The input stream to read user input.
     * @return The inputted full name.
     */
    public static String fullNameInput(MyInputStream bf) {
        while (true) {
            System.out.print("Введите полное название организации: ");
            String fullName = MyInputStream.Scan();

            // Validate that the full name is not an empty string
            if (fullName.replaceAll(" ", "").isEmpty()) {
                System.out.println("Вы ввели пустую строку, она не может быть названием организации!");
            } else {
                return fullName;
            }
        }
    }
}
