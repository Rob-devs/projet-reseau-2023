package miage.reseau.projet.commands;

import miage.reseau.projet.commands.command.Command;
import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;
import miage.reseau.projet.server.Server;
import miage.reseau.projet.server.Value;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;

import java.util.Arrays;

/**
 * Permet de connaitre le nombre de clés fournies en paramètre qui existent dans
 * le stockage
 */
public class Exists implements Command {

    private final Server server;
    private final String commandName = Constants.COMMAND_EXISTS;

    public Exists(Server server) {
        this.server = server;
    }

    /**
     * This function checks if a given request matches a specific command name.
     * 
     * @param request An object of type ContentRESP, which is being passed as a
     *                parameter to the
     *                matches() method.
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
        if (requestData.length < 2) {
            return new ContentRESP(TypeRESP.ERROR, LogBuilder.getArgumentsErrorMessage(commandName));
        }

        int count = 0;
        for (int i = 1; i < requestData.length; i++) {
            String key = (String) requestData[i].getContent();
            Value storedValue = server.getStore().getData(key);
            if (storedValue != null) {
                count++;
            }
        }

        return new ContentRESP(TypeRESP.INT, count);

    }
}
