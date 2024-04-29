/**
 * The {@code TurnoverInput} class provides a method to input and validate the annual turnover of an organization.
 * It prompts the user to input the annual turnover and ensures that it is a positive integer value.
 */
package input;

public class TurnoverInput {

    /**
     * Prompts the user to input the annual turnover of an organization, validating that it is a positive integer.
     *
     * @param bf The input stream to read user input.
     * @return The inputted annual turnover as an {@code Integer}.
     */
    public static Integer turnoverInput(MyInputStream bf) {
        while (true) {
            System.out.print("Введите годовой оборот: ");
            String turnover = MyInputStream.Scan();

            // Validate that the input is not empty
            if (turnover.replaceAll(" ", "").isEmpty()) {
                System.out.println("Оборот не может принимать значение null!");
            } else {
                try {
                    int balance = Integer.parseInt(turnover);

                    // Validate that the turnover is a positive value
                    if (balance > 0) {
                        return Integer.parseInt(turnover);
                    } else {
                        System.out.println("Оборот не может принимать отрицательное значение.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Нужно ввести Integer!");
                }
            }
        }
    }
}
