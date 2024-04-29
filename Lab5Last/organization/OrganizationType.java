/**
 * The {@code OrganizationType} enum represents the different types of organizations.
 * It includes commercial, public, private limited company, and open joint-stock company.
 */
package organization;

import jakarta.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum OrganizationType {
    /**
     * Commercial organization type.
     */
    COMMERCIAL,

    /**
     * Public organization type.
     */
    PUBLIC,

    /**
     * Private limited company organization type.
     */
    PRIVATE_LIMITED_COMPANY,

    /**
     * Open joint-stock company organization type.
     */
    OPEN_JOINT_STOCK_COMPANY
}
