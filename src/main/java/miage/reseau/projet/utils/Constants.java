package miage.reseau.projet.utils;

public class Constants {

    // Port
    public static final int NUM_PORT = 6379;

    // Response
    public static final char RESP_STRING = '+';
    public static final char RESP_STRING_BULK = '$';
    public static final char RESP_INT = ':';
    public static final char RESP_ARRAY = '*';
    public static final char RESP_ERROR = '-';
    public static final char RESP_OTHER = 0;

    // Other
    public static final String CLIENT_CONNECT = "CONNECTION";
    public static final String CLIENT_DISCONNECT = "DISCONNECTION";
}
