/**
 * The {@code CoordinateInput} class provides a method to input and create an {@link organization.Coordinates} instance.
 * It prompts the user to input both a double value for x and an Integer value for y, validating their formats and ranges.
 */
package input;

import organization.Coordinates;

public class CoordinateInput {

    /**
     * Prompts the user to input a double value for x and an Integer value for y, creating a {@link organization.Coordinates} instance.
     *
     * @param bf The input stream to read user input.
     * @return The created {@code Coordinates} instance.
     */
    public static Coordinates input(MyInputStream bf) {
        double x;
        Integer y;

        // Input and validate the double value for x
        while (true) {
            try {
                System.out.print("Введите (double)х: ");
                x = Double.parseDouble(MyInputStream.Scan());

                // Validate the range of x
                if (x > 264) {
                    System.out.println("Максимальное значение x равно 264, повторите ввод");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Надо ввести значение double!");
            }
        }

        // Input and validate the Integer value for y
        while (true) {
            try {
                System.out.print("Введите (Integer)y: ");
                y = Integer.parseInt(MyInputStream.Scan());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Надо ввести значение Integer!");
            }
        }

        return new Coordinates(x, y);
    }
}
