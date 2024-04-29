/**
 * The {@code AddressInput} class provides a method to input and create an {@link organization.Address} instance.
 * It prompts the user to input a ZIP code and validates its length before creating the Address object.
 */
package input;

import organization.Address;

public class AddressInput {

    /**
     * Prompts the user to input a ZIP code and creates an {@link organization.Address} instance.
     *
     * @param bf The input stream to read user input.
     * @return The created {@code Address} instance.
     */
    public static Address input(MyInputStream bf) {
        while (true) {
            System.out.println("Введите зип код:");
            String zipCode = MyInputStream.Scan();

            // Validate the length of the ZIP code
            if (zipCode.length() > 22) {
                System.out.println("Длина zipCode не может быть больше 22 символов, повторите ввод");
            } else {
                return new Address(zipCode);
            }
        }
    }
}
