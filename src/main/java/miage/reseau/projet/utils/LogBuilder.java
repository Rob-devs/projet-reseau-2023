package miage.reseau.projet.utils;

public class LogBuilder {

    /**
     * This function returns a string message for invalid input.
     * 
     * @return The method is returning a String message "Invalid request entry".
     */
    public static String getInvalidInputMessage() {
        return "Invalid request entry";
    }

    /**
     * The function returns a message indicating that a server has started at a
     * specified port.
     * 
     * @param port The port number on which the server is started.
     * @return A string message indicating that the server has started at a specific
     *         port. The message
     *         includes the port number.
     */
    public static String getStartServerMessage(int port) {
        return "Server started at " + port;
    }

    /**
     * This function returns a message indicating that no command was found.
     * 
     * @return The method is returning a string message "No command found".
     */
    public static String getNoCmdFoundMessage() {
        return "No command found";
    }

    /**
     * This function returns a message indicating that a command with a given name
     * already exists.
     * 
     * @param name The name of the command that already exists.
     * @return A string message stating that a command with the given name already
     *         exists. The message
     *         is constructed by concatenating the string "Command ", the given
     *         name, and the string " already
     *         exists".
     */
    public static String getCmdAlreadyExistsMessage(String name) {
        return "Command " + name + " already exists";
    }

    /**
     * The function returns a message indicating that a command has been
     * successfully added.
     * 
     * @param name The name of the command that was successfully added.
     * @return A string message that says "Command [name] successfully added", where
     *         [name] is the value
     *         of the parameter passed to the method.
     */
    public static String getCmdSuccessfulyAddedMessage(String name) {
        return "Command " + name + " successfully added";
    }

    public static String getKeyDoesNotExistMessage() {
        return "Key does not exist";
    }

    /**
     * The function returns a message indicating the number of clients and their
     * connection status.
     * 
     * @param status  A String variable that represents the status of a client
     *                connection. It can be either "CLIENT_CONNECT" or
     *                "CLIENT_DISCONNECT".
     * @param clients The number of clients connected to a server.
     * @return The method returns a String message that includes the number of
     *         clients and a status indicator (+ for connect, - for disconnect).
     */
    public static String getConnectionStatusMessage(String status, int clients) {
        String message = "Clients : " + clients;

        if (status.equals(Constants.CLIENT_CONNECT)) {
            message += " [+]";
        } else if (status.equals(Constants.CLIENT_DISCONNECT)) {
            message += " [-]";
        }

        return message;
    }
}
