/**
 * The {@code OrganizationTypeInput} class provides a method to input and validate the type of an organization.
 * It prompts the user to input the type number and validates it to ensure it falls within the specified range.
 */
package input;

import organization.OrganizationType;

public class OrganizationTypeInput {

    /**
     * Prompts the user to input the number corresponding to the type of organization.
     * The valid options are: 1 for COMMERCIAL, 2 for PUBLIC, 3 for PRIVATE_LIMITED_COMPANY, and 4 for OPEN_JOINT_STOCK_COMPANY.
     *
     * @param bf The input stream to read user input.
     * @return The selected {@code OrganizationType}.
     */
    public static OrganizationType input(MyInputStream bf) {
        while (true) {
            System.out.println("Введите номер типа организации");
            System.out.println("1 - COMMERCIAL,\n2 - PUBLIC,\n3 - PRIVATE_LIMITED_COMPANY\n4 - OPEN_JOINT_STOCK_COMPANY");
            String type = MyInputStream.Scan();

            // Validate that the input is not empty
            if (type.replaceAll(" ", "").isEmpty()) {
                System.out.println("Вы ввели пустое поле, введите номер типа организации");
            } else {
                try {
                    int tp = Integer.parseInt(type);

                    // Validate that the input falls within the specified range
                    if (tp < 1 || tp > 4) {
                        System.out.println("Вы указали неправильный номер! Проверьте и введите еще раз");
                    } else {
                        // Map the input number to the corresponding OrganizationType
                        OrganizationType orgType = switch (tp) {
                            case 1 -> OrganizationType.COMMERCIAL;
                            case 2 -> OrganizationType.PUBLIC;
                            case 3 -> OrganizationType.PRIVATE_LIMITED_COMPANY;
                            case 4 -> OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                            default -> null; // This should never occur
                        };

                        return orgType;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Нужно ввести int!");
                }
            }
        }
    }
}
