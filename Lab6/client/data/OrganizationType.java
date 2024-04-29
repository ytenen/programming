package data;

import jakarta.xml.bind.annotation.XmlEnum;

import java.io.Serializable;

@XmlEnum
public enum OrganizationType implements Serializable {
    COMMERCIAL,
    PUBLIC,
    PRIVATE_LIMITED_COMPANY,
    OPEN_JOINT_STOCK_COMPANY;
}
