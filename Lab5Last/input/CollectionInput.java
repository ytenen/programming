/**
 * The {@code CollectionInput} class provides methods to input and create instances of the {@link organization.Organization}
 * class, using various input methods for different fields like name, full name, address, coordinates, annual turnover, and organization type.
 */
package input;

import dataManager.idStorage;
import organization.Address;
import organization.Coordinates;
import organization.Organization;
import organization.OrganizationType;

public class CollectionInput {

    /**
     * Prompts the user to input various details and creates an {@link organization.Organization} instance.
     *
     * @param bf The input stream to read user input.
     * @return The created {@code Organization} instance.
     */
    public static Organization organizationInput(MyInputStream bf) {
        String name = NameInput.nameInput(bf);
        String fullName = NameInput.fullNameInput(bf);
        long id = idStorage.getId();
        Address address = AddressInput.input(bf);
        Coordinates coordinates = CoordinateInput.input(bf);
        Integer annualTurnover = TurnoverInput.turnoverInput(bf);
        OrganizationType type = OrganizationTypeInput.input(bf);

        return new Organization(name, fullName, id, coordinates, address, annualTurnover, type);
    }
}
