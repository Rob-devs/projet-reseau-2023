package miage.reseau.projet.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a data store for storing key-value pairs in Redis.
 */
public class Store {

    private Map<String, Value> dataMap;

    /**
     * Constructs a new Store object with an empty data map.
     */
    public Store() {
        this.dataMap = new HashMap<>();
    }

    /**
     * Saves the provided value associated with the specified key in the data store.
     *
     * @param key   the key to associate with the value
     * @param value the value to be saved
     */
    public void saveData(String key, Value value) {
        dataMap.put(key, value);
    }

    /**
     * Retrieves the value associated with the specified key from the data store.
     *
     * @param key the key to retrieve the value for
     * @return the value associated with the key, or null if the key is not found
     */
    public Value getData(String key) {
        return dataMap.get(key);
    }

    /**
     * Removes the value associated with the specified key from the data store.
     *
     * @param key the key to remove along with its associated value
     * @return the value that was removed, or null if the key is not found
     */
    public Value delData(String key) {
        return dataMap.remove(key);
    }

}