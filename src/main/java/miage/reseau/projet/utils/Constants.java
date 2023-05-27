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
    
    public static final String COMMAND_APPEND = "APPEND";
    public static final String COMMAND_DECREMENT = "DECR";
    public static final String COMMAND_INCREMENT = "INCR";
    public static final String COMMAND_EXISTS = "EXISTS";
    public static final String COMMAND_EXPIRE = "EXPIRE";
    public static final String COMMAND_GET = "GET";
    public static final String COMMAND_SET = "SET";
    public static final String COMMAND_SETNX = "SETNX";
    public static final String COMMAND_STRLEN = "STRLEN";
    public static final String COMMAND_INFO = "INFO";
    public static final String COMMAND_DELETE = "DEL";
}
