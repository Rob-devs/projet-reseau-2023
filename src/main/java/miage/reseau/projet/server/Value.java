package miage.reseau.projet.server;

import miage.reseau.projet.resp.ContentRESP;

/**
 * Represents a value stored in Redis along with its expiration time.
 */
public class Value {

    private static final double DEFAULT_EXPIRATION = Double.MAX_VALUE;

    private ContentRESP value;
    private double expiration;

    /**
     * Constructs a Value object with the specified content and default expiration
     * time.
     *
     * @param value the content of the value
     */
    public Value(ContentRESP value) {
        this(value, DEFAULT_EXPIRATION);
    }

    /**
     * Constructs a Value object with the specified content and expiration time.
     *
     * @param value      the content of the value
     * @param expiration the expiration time in milliseconds
     */
    public Value(ContentRESP value, double expiration) {
        this.value = value;
        setExpiration(expiration);
    }

    /**
     * Returns the content of the value.
     *
     * @return the content of the value
     */
    public ContentRESP getValue() {
        return value;
    }

    /**
     * Sets the content of the value.
     *
     * @param value the content to set
     */
    public void setValue(ContentRESP value) {
        this.value = value;
    }

    /**
     * Returns the expiration time of the value in milliseconds.
     *
     * @return the expiration time in milliseconds
     */
    public double getExpiration() {
        return expiration;
    }

    /**
     * Sets the expiration time of the value.
     *
     * @param expiration the expiration time in milliseconds
     */
    public void setExpiration(double expiration) {
        this.expiration = System.currentTimeMillis() + expiration;
    }

    /**
     * Checks if the value has expired.
     *
     * @return true if the value has expired, false otherwise
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > expiration;
    }
}
