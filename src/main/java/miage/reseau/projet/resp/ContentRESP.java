package miage.reseau.projet.resp;

/**
 * The ContentRESP class defines a data structure that holds a type and content
 * object, with methods to
 * check the type and get/set the type and content.
 */
public class ContentRESP {

    private TypeRESP type;
    private Object content;

    // This is a constructor method for the `ContentRESP` class that takes in two
    // parameters: a
    // `TypeRESP` object and an `Object` object. It initializes the `type` and
    // `content` instance
    // variables of the `ContentRESP` object being created with the values of the
    // `type` and `content`
    // parameters passed in.
    public ContentRESP(TypeRESP type, Object content) {
        this.type = type;
        this.content = content;
    }

    public ContentRESP() {
    }

    /**
     * The function checks if the type of an object is an array.
     * 
     * @return The method `isArray()` is returning a boolean value. The value being
     *         returned depends on
     *         the implementation of the method `isOfType(TypeRESP.ARRAY)`. If the
     *         object being checked is an
     *         array, then `isOfType(TypeRESP.ARRAY)` will return true and
     *         `isArray()` will return true.
     *         Otherwise, `isOfType(TypeRESP.ARRAY)` will return false and
     *         `isArray()` will return false
     */
    public boolean isArray() {
        return isOfType(TypeRESP.ARRAY);
    }

    /**
     * The function checks if a given object is of a specific type.
     * 
     * @param type The parameter "type" is a variable of the TypeRESP enum type. The
     *             method "isOfType"
     *             checks if the current object's type is equal to the input type
     *             parameter. It returns a boolean
     *             value of true if they are equal, and false otherwise.
     * @return A boolean value is being returned.
     */
    public boolean isOfType(TypeRESP type) {
        return this.type == type;
    }

    public boolean isTypeNull() {
        return type == null;
    }

    // #region Getters and setters
    public void setType(TypeRESP type) {
        this.type = type;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public TypeRESP getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }
    // #region
}
