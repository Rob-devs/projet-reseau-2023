package miage.reseau.projet.commands;

import miage.reseau.projet.server.Server;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.commands.command.Command;
import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;

import java.util.Arrays;

/**
 * Permet d'obtenir des informations à propos du serveur Redis courant
 */
public class Info implements Command {

    private final Server server;
    private final String commandName = Constants.COMMAND_INFO;

    public Info(Server server) {
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

        String resultBuilder = "# SERVER INFO\r\n" +
                "- java_version: " + System.getProperty("java.version") + "\r\n" +
                "- operating_system_name: " + System.getProperty("os.name") + "\r\n" +
                "- operating_system_version: " + System.getProperty("os.version") + "\r\n" +
                "- architecture: " + System.getProperty("os.arch") + "\r\n" +
                "- uptime: " + (System.currentTimeMillis() - server.getStart()) / 1000 + " seconds\r\n" +
                "\r\n" +
                "# CONNECTIONS\r\n" +
                "- connected_clients: " + server.getClients() + "\r\n";

        return new ContentRESP(TypeRESP.STRING_BULK, resultBuilder);
    }
}
