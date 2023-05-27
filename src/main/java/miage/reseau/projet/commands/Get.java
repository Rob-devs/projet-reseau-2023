package miage.reseau.projet.commands;

import miage.reseau.projet.server.Server;
import miage.reseau.projet.server.Value;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;

import java.util.Arrays;

import miage.reseau.projet.commands.command.Command;
import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;

/**
 * Permet de récupérer la valeur d'une clé redis.
 */
public class Get implements Command {

    private final Server server;
    private final String commandName = Constants.COMMAND_GET;

    public Get(Server server) {
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

    @Override
    public ContentRESP execute(ContentRESP request) {
        ContentRESP[] requestData = (ContentRESP[]) request.getContent();
        if (requestData.length != 2) {
            return new ContentRESP(TypeRESP.ERROR,
                    LogBuilder.getArgumentsErrorMessage(commandName));
        }
        String key = (String) requestData[1].getContent();
        Value value = server.getStore().getData(key);

        if (value == null) {

            return new ContentRESP(TypeRESP.STRING_BULK, null);
        } else {

            ContentRESP data = value.getValue();

            if (data.getType() == TypeRESP.STRING_BULK) {
                String stringValue = (String) data.getContent();

                // Return key value if GET was executed correctly
                return new ContentRESP(TypeRESP.STRING_BULK, stringValue);
            } else {

                return new ContentRESP(TypeRESP.ERROR, "ERR La valeur n'est pas une chaîne de caractères.");
            }
        }
    }
}
