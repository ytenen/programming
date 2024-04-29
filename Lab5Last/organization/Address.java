/**
 * The {@code Address} class represents the address of an organization.
 * It includes a zip code as a unique identifier for the address.
 */
package organization;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address {

    /**
     * The zip code associated with the address.
     */
    private String zipCode;

    /**
     * Constructs an {@code Address} object with the specified zip code.
     *
     * @param zipCode The zip code of the address.
     */
    public Address(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the zip code of the address.
     *
     * @return The zip code as a {@code String}.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the address.
     *
     * @param zipCode The new zip code to be set.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
