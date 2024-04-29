/**
 * The {@code OrganizationManager} class is responsible for managing the collection of organizations in the application.
 * It follows the singleton pattern to ensure a single instance throughout the application.
 * It also provides methods to access and manipulate the organization collection.
 */
package dataManager;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import organization.Organization;

import java.util.ArrayDeque;
import java.util.Date;

@XStreamAlias("organizationManager")
public class OrganizationManager {

    /**
     * The collection of organizations managed by the {@code OrganizationManager}.
     */
    @XStreamImplicit(itemFieldName = "organization")
    ArrayDeque<Organization> collection;

    /**
     * The singleton instance of {@code OrganizationManager}.
     */
    private static OrganizationManager instance;

    /**
     * The date when the {@code OrganizationManager} is initialized.
     */
    private final Date date;

    /**
     * Private constructor to enforce the singleton pattern and initialize the organization collection and date.
     */
    public OrganizationManager() {
        collection = new ArrayDeque<>();
        date = new Date();
    }

    /**
     * Gets the singleton instance of {@code OrganizationManager}.
     *
     * @return The singleton instance of {@code OrganizationManager}.
     */
    public static OrganizationManager getInstance() {
        if (instance == null) {
            instance = new OrganizationManager();
        }
        return instance;
    }

    /**
     * Gets the collection of organizations.
     *
     * @return The collection of organizations.
     */
    public ArrayDeque<Organization> getCollection() {
        return collection;
    }

    /**
     * Sets the collection of organizations.
     *
     * @param collection The new collection of organizations.
     */
    public void setCollection(ArrayDeque<Organization> collection) {
        this.collection = collection;
    }

    /**
     * Adds an organization to the collection.
     *
     * @param organization The organization to be added.
     */
    public void addElement(Organization organization) {
        collection.add(organization);
    }

    /**
     * Gets the date when the {@code OrganizationManager} is initialized.
     *
     * @return The initialization date.
     */
    public Date getDate() {
        return date;
    }
}
