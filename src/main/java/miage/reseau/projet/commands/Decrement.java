package miage.reseau.projet.commands;

import miage.reseau.projet.server.Server;
import miage.reseau.projet.server.Value;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;
import miage.reseau.projet.commands.command.Command;
import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;

public class Decrement implements Command {

    private final Server server;
    private final String commandName = Constants.COMMAND_DECREMENT;

    public Decrement(Server server) {
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
        ContentRESP errorResponse = new ContentRESP(TypeRESP.ERROR, LogBuilder.getArgumentsErrorMessage(commandName));

        if (requestData.length < 2) {
          return errorResponse;
        }

        String key = (String) requestData[1].getContent();
        Value oldValue = server.getStore().getData(key);
        if (oldValue == null) {
            oldValue = new Value(new ContentRESP(TypeRESP.STRING_BULK, "0"));
        }
        try {
            int oldValueContent = Integer.parseInt((String) oldValue.getValue().getContent());
            int newValue = oldValueContent - 1;
            ContentRESP newContent = new ContentRESP(TypeRESP.STRING_BULK, Integer.toString(newValue));
            oldValue.setValue(newContent);
            server.getStore().saveData(key, oldValue);
            return newContent;
        } catch (NumberFormatException ignore) {
            return new ContentRESP(TypeRESP.ERROR, "Erreur : Valeur non valide");
        }

    }