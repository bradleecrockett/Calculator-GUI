package gui;

import javafx.beans.property.StringProperty;

/**
 * Postal code search interface
 */
public interface PostalCode {

    /**
     * Search for the city name from a postal code
     *
     * @param postalCode to search for
     */
    void search(String postalCode);

    /**
     * Get the city name property
     *
     * @return the city name property
     */
    StringProperty getCityNameProperty();

    /**
     * Get the city name
     *
     * @return the city name
     */
    String getCityName();
}
