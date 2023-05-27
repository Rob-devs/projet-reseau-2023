package miage.reseau.projet.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of string values in a key-value format.
 */
public class Values {

    private Map<String, Value> stringRESPDataMap;

    /**
     * Constructs an empty instance of the Values class.
     */
    public Values() {
        this(new HashMap<>());
    }

    /**
     * Constructs an instance of the Values class with the specified map of string
     * values.
     *
     * @param stringRESPDataMap the map of string values
     */
    public Values(Map<String, Value> stringRESPDataMap) {
        this.stringRESPDataMap = stringRESPDataMap;
    }

    /**
     * Returns the map of string values.
     *
     * @return the map of string values
     */
    public Map<String, Value> getStringRESPDataMap() {
        return stringRESPDataMap;
    }

    /**
     * Sets the map of string values.
     *
     * @param stringRESPDataMap the map of string values to set
     */
    public void setStringRESPDataMap(Map<String, Value> stringRESPDataMap) {
        this.stringRESPDataMap = stringRESPDataMap;
    }
}
