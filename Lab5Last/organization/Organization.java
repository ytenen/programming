/**
 * The {@code Organization} class represents information about a specific organization.
 * It includes details such as name, coordinates, creation date, annual turnover, full name,
 * organization type, and official address.
 */
package organization;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.ZonedDateTime;

@XmlRootElement
public class Organization {

    /**
     * The unique identifier for the organization.
     * It should be greater than 0 and generated automatically.
     */
    private final long id;

    /**
     * The name of the organization. It cannot be null and must not be an empty string.
     */
    private String name;

    /**
     * The coordinates of the organization's location. It cannot be null.
     */
    private Coordinates coordinates;

    /**
     * The date and time when the organization was created. It cannot be null and is generated automatically.
     */
    private final ZonedDateTime creationDate = ZonedDateTime.now();

    /**
     * The annual turnover of the organization. It should be greater than 0.
     */
    private Integer annualTurnover;

    /**
     * The full name of the organization. It cannot be null.
     */
    private String fullName;

    /**
     * The type of the organization. It can be null.
     */
    private OrganizationType type;

    /**
     * The official address of the organization. It cannot be null.
     */
    private Address officialAddress;

    /**
     * Constructs an {@code Organization} object with the specified parameters.
     *
     * @param name           The name of the organization.
     * @param fullName       The full name of the organization.
     * @param id             The unique identifier for the organization.
     * @param coordinates    The coordinates of the organization's location.
     * @param address        The official address of the organization.
     * @param annualTurnover The annual turnover of the organization.
     * @param type           The type of the organization.
     */
    public Organization(String name, String fullName, long id, Coordinates coordinates, Address address, Integer annualTurnover, OrganizationType type) {
        this.name = name;
        this.fullName = fullName;
        this.id = id;
        this.coordinates = coordinates;
        this.officialAddress = address;
        this.annualTurnover = annualTurnover;
        this.type = type;
    }

    /**
     * Gets the unique identifier of the organization.
     *
     * @return The unique identifier as a {@code long}.
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the name of the organization.
     *
     * @return The name as a {@code String}.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the coordinates of the organization's location.
     *
     * @return The coordinates as a {@code Coordinates} object.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets the creation date and time of the organization.
     *
     * @return The creation date as a {@code ZonedDateTime} object.
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Gets the annual turnover of the organization.
     *
     * @return The annual turnover as an {@code Integer}.
     */
    public Integer getAnnualTurnover() {
        return annualTurnover;
    }

    /**
     * Gets the full name of the organization.
     *
     * @return The full name as a {@code String}.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Gets the type of the organization.
     *
     * @return The organization type as an {@code OrganizationType} object.
     */
    public OrganizationType getOrganizationType() {
        return type;
    }

    /**
     * Gets the official address of the organization.
     *
     * @return The official address as an {@code Address} object.
     */
    public Address getOfficialAddress() {
        return officialAddress;
    }

    /**
     * Sets the name of the organization.
     *
     * @param name The new name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the coordinates of the organization's location.
     *
     * @param coordinates The new coordinates to be set.
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Sets the annual turnover of the organization.
     *
     * @param annualTurnover The new annual turnover to be set.
     */
    public void setAnnualTurnover(Integer annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    /**
     * Sets the full name of the organization.
     *
     * @param fullName The new full name to be set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Sets the type of the organization.
     *
     * @param type The new organization type to be set.
     */
    public void setOrganizationType(OrganizationType type){
        this.type = type;
    }

    public void setAddress(Address address){
        this.officialAddress = address;
    }

}
