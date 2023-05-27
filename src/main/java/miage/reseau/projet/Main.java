package miage.reseau.projet;

import miage.reseau.projet.server.Server;

/**
 * Entry point of the application.
 */
public class Main {

    /**
     * Main method of the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        Server server = new Server();
        server.getCommands().initializeCommands(commands->
        {
            commands.addCommand(new Info(server));
            commands.addCommand(new Set(server));
            commands.addCommand(new Setnx(server));
            commands.addCommand(new Expire(server));
            commands.addCommand(new Exists(server));
            commands.addCommand(new Append(server));
            commands.addCommand(new Increment(server));
            commands.addCommand(new Decrement(server));
            commands.addCommand(new Get(server));
            commands.addCommand(new Strlen(server));
            commands.addCommand(new Delete(server));
        });

        server.start();
    }
}
