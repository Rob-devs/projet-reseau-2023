package miage.reseau.projet.resp;

import miage.reseau.projet.utils.Constants;

// This code defines an enum called `RESPType` that represents the different types of responses in the
// Redis Serialization Protocol (RESP). The enum has six possible values: `STRING`, `STRING_BULK`,
// `INT`, `ARRAY`, `ERROR`, and `OTHER`.
public enum TypeRESP {

    // These are the possible values of the `TypeRESP` enum, which represents the
    // different types of
    // responses in the Redis Serialization Protocol (RESP). Each value corresponds
    // to a different type
    // of response that can be received or sent in the protocol.
    STRING,
    STRING_BULK,
    INT,
    ARRAY,
    ERROR,
    OTHER;

    /**
     * The function returns a RESPType based on the input character value.
     * 
     * @param value a character value that represents the type of response in a
     *              Redis protocol called
     *              RESP (REdis Serialization Protocol).
     * @return The method is returning a RESPType enum value based on the input
     *         character value. The
     *         possible enum values are STRING, STRING_BULK, INT, ARRAY, ERROR, and
     *         OTHER.
     */
    public static TypeRESP getTypeFromChar(char value) {
        if (value == Constants.RESP_STRING)
            return TypeRESP.STRING;
        else if (value == Constants.RESP_STRING_BULK)
            return TypeRESP.STRING_BULK;
        else if (value == Constants.RESP_INT)
            return TypeRESP.INT;
        else if (value == Constants.RESP_ARRAY)
            return TypeRESP.ARRAY;
        else if (value == Constants.RESP_ERROR)
            return TypeRESP.ERROR;
        else
            return TypeRESP.OTHER;
    }

    /**
     * This function returns a character based on the input RESPType value.
     * 
     * @param value a variable of type RESPType, which is an enum that represents
     *              the different types
     *              of responses in the Redis Serialization Protocol (RESP).
     * @return A character value is being returned based on the input RESPType
     *         value. The character
     *         returned corresponds to the type of the RESP value.
     */
    public static char getCharFromType(TypeRESP value) {
        if (value == TypeRESP.STRING)
            return Constants.RESP_STRING;
        else if (value == TypeRESP.STRING_BULK)
            return Constants.RESP_STRING_BULK;
        else if (value == TypeRESP.INT)
            return Constants.RESP_INT;
        else if (value == TypeRESP.ARRAY)
            return Constants.RESP_ARRAY;
        else if (value == TypeRESP.ERROR)
            return Constants.RESP_ERROR;
        else
            return Constants.RESP_OTHER;
    }
}
