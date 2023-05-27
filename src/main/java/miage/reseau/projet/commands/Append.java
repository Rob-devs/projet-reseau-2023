package miage.reseau.projet.commands;

import miage.reseau.projet.server.Server;
import miage.reseau.projet.server.Value;

import java.util.Arrays;

import miage.reseau.projet.commands.command.Command;
import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;


/**
 * Concatene a une valeur si elle existe, la cree sinon
 */
public class Append implements Command {

    private final Server server;
    private final String commandName = Constants.COMMAND_APPEND;

    public Append(Server server) {
        this.server = server;
    }

    /**
     * This function checks if a given request matches a specific command name.
     * 
     * @param request An object of type ContentRESP, which is being passed as a parameter to the
     * matches() method.
     * @return A boolean value is being returned.
     */
    @Override
    public boolean matches(ContentRESP request) {

        return request.isArray() &&
                Arrays.stream((ContentRESP[]) request.getContent())
                        .findFirst()
                        .map(contentRESP -> ((String) contentRESP.getContent()).equalsIgnoreCase(commandName))
                        .orElse(false);
    }

    /**
     * This Java function concatenates a new value to an existing Redis value or saves a new Redis
     * value if it doesn't exist.
     * 
     * @param request ContentRESP object representing the request to execute the command.
     * @return The method is returning a ContentRESP object. The type of the ContentRESP object
     * returned depends on the logic of the code execution. It could be an error message if the request
     * content length is less than 3, or an integer value representing the length of the concatenated
     * string if the stored value is not null, or the length of the string value if the stored value is
     * null.
     */
    @Override
    public ContentRESP execute(ContentRESP request) {

        ContentRESP[] requestContent = (ContentRESP[]) request.getContent();
        if (requestContent.length < 3) {
            return new ContentRESP(TypeRESP.ERROR, LogBuilder.getArgumentsErrorMessage(commandName));
        }

        String key = (String) requestContent[1].getContent();
        Value newValue = new Value(requestContent[2]);

        Value storedValue = this.server.getStore().getData(key);
        String stringValue = (String) newValue.getValue().getContent();

        if (storedValue != null) {
            String concatenatedValue = (String) storedValue.getValue().getContent() + stringValue;
            storedValue.getValue().setContent(concatenatedValue);
            return new ContentRESP(TypeRESP.INT, concatenatedValue.length());
        } else {
            this.server.getStore().saveData(key, newValue);
            return new ContentRESP(TypeRESP.INT, stringValue.length());
        }
    }
}
