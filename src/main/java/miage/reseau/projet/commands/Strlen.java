package miage.reseau.projet.commands;

import miage.reseau.projet.commands.command.Command;
import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;
import miage.reseau.projet.server.Server;
import miage.reseau.projet.server.Value;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;

/**
 * Permet de récupérer la longueur d'une chaîne de caractères associée à une clé
 * donnée
 */
public class Strlen implements Command {

	private final Server server;
	private final String commandName = Constants.COMMAND_STRLEN;

	public Strlen(Server server) {
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
        TypeRESP responseType = TypeRESP.ERROR;
        String errorMessage = LogBuilder.getArgumentsErrorMessage(commandName);
        ContentRESP errorResponse = new ContentRESP(responseType, errorMessage);
        return errorResponse;
    }

    String key = (String) requestData[1].getContent();
    Value value = server.getStore().getData(key);

    if (value == null) {
        return new ContentRESP(TypeRESP.INT, 0);
    } else {
        ContentRESP data = value.getValue();
        TypeRESP responseType;
        Object content;
        if (data.getType() == TypeRESP.STRING_BULK) {
            String stringValue = (String) data.getContent();
            responseType = TypeRESP.INT;
            content = stringValue.length();
            return new ContentRESP(responseType, content);
        } else {
            return new ContentRESP(TypeRESP.ERROR, "Erreur : Pas une String");
        }
    }
}

}
