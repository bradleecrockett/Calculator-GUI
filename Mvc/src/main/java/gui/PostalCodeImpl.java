package gui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Postal code implementation using GeoNames
 */
public class PostalCodeImpl implements PostalCode {
    private static final String ERROR_MESSAGE = "Error searching for postal code";

    private final StringProperty cityName;

    /**
     * Constructor
     */
    public PostalCodeImpl() {
        cityName = new SimpleStringProperty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void search(String postalCode) {
        StringBuilder json = new StringBuilder();

        Runnable searchThread = () -> {

            try {
                URL url = new URL("http://api.geonames.org/postalCodeSearchJSON?postalcode=" + postalCode + "&username=steve");
                try (InputStream is = url.openStream()) {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while ((line = rd.readLine()) != null) {
                        json.append(line);
                    }
                } catch (IOException e) {
                    cityName.setValue(ERROR_MESSAGE);
                }
            } catch (MalformedURLException e) {
                cityName.setValue(ERROR_MESSAGE);
            }

            // check for a status message
            JsonParser parser = new JsonParser();
            JsonElement jseRoot = parser.parse(json.toString().trim());
            JsonElement status = jseRoot.getAsJsonObject().get("status message");
            if (status != null) {
                cityName.setValue(ERROR_MESSAGE);
            }

            // fetch the city name

            JsonArray results = jseRoot.getAsJsonObject()
                    .get("postalCodes")
                    .getAsJsonArray();

            if (results.size() != 0) {
                cityName.setValue(results.getAsJsonObject()
                        .get("placeName")
                        .getAsString());
            } else {
                cityName.setValue(ERROR_MESSAGE);
            }
        };
        searchThread.run();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringProperty getCityNameProperty() {
        return cityName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCityName() {
        return cityName.get();
    }
}
