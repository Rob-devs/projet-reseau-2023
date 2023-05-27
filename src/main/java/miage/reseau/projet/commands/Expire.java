package miage.reseau.projet.commands;

import miage.reseau.projet.server.Server;
import miage.reseau.projet.server.Store;
import miage.reseau.projet.server.Value;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;
import miage.reseau.projet.commands.command.Command;
import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;

import java.util.Arrays;

/**
 * Définit un temps d'expiration sur une clé existante
 */
public class Expire implements Command {

    private final Server server;
    private final String commandName = Constants.COMMAND_EXPIRE;

    public Expire(Server server) {
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
        if (requestData.length < 2) {
            return new ContentRESP(TypeRESP.ERROR, LogBuilder.getArgumentsErrorMessage(commandName));
        }

        String key = (String) requestData[1].getContent();
        Store serverStore = server.getStore();
        Value storedValue = serverStore.getData(key);

        if (storedValue == null || requestData.length == 2) {
            // 0 if the timeout was not set. e.g. key doesn't exist.
            return new ContentRESP(TypeRESP.INT, 0);
        }

        long seconds;
        try {
            seconds = Long.parseLong((String) requestData[2].getContent());
        } catch (NumberFormatException e) {
            return new ContentRESP(TypeRESP.ERROR, "ERR Le format du nombre en paramètre pour l'expiration est incorrect, " +
                    "il doit être inférieur à " + Long.MAX_VALUE + " et supérieur à " + Long.MIN_VALUE);
        }

        storedValue.setExpiration(System.currentTimeMillis() + (seconds * 1000L));
        serverStore.saveData(key, storedValue);

        // 1 if the timeout was set.
        return new ContentRESP(TypeRESP.INT, 1);
    }
}
