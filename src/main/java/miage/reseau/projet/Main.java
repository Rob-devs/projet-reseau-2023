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

        server.start();
    }
}