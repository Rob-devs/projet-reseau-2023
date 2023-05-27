package miage.reseau.projet.server;

import java.net.Socket;

import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.SReaderRESP;
import miage.reseau.projet.resp.SWriterRESP;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;

/**
 * The Client class handles client connections and requests,
 * executes server commands, and sends responses back to the client.
 */
public class Client implements Runnable {

    private final Socket clientSocket;
    private final Server server;

    public Client(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    /**
     * This function handles client connections and requests, executes server
     * commands, and sends responses back to the client.
     */
    @Override
    public void run() {
        try {
            ContentRESP clientRequest;
            ContentRESP serverResponse;
            SReaderRESP inputStreamReader = new SReaderRESP(clientSocket.getInputStream());
            SWriterRESP outputStreamWriter = new SWriterRESP(clientSocket.getOutputStream());

            while (true) {
                clientRequest = inputStreamReader.readData();
                serverResponse = server.getCommands().executeCommand(clientRequest);
                outputStreamWriter.write(serverResponse);
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
