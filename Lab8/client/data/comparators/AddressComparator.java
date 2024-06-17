package data.comparators;

import data.Organization;

import java.util.Comparator;

public class AddressComparator implements Comparator<Organization> {
    @Override
    public int compare(Organization o1, Organization o2) {
        return o1.getOfficialAddress().getZipCode().compareTo(o2.getOfficialAddress().getZipCode());
    }
}
