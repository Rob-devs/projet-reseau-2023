package miage.reseau.projet.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import miage.reseau.projet.commands.command.Commands;
import miage.reseau.projet.utils.Constants;
import miage.reseau.projet.utils.LogBuilder;

/**
 * The Server class creates a server socket on a specified port and accepts
 * incoming client connections, creating a new thread for each client.
 */
public class Server {

    private int port;
    private int clients;

    private double start;
    private boolean isRunning;
    private Store store;

    private Commands commands = new Commands();

    public Server() {
        this.port = Constants.NUM_PORT;
        this.clients = 0;
        this.store = new Store();
    }

    public Server(int port) {
        this.port = port;
        this.clients = 0;
        this.store = new Store();
    }

    /**
     * This function starts a server socket on a specified port and accepts incoming
     * client connections, creating a new thread for each client.
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {

            System.out.println(LogBuilder.getStartServerMessage(this.port));

            try {
                ExecutorService executor = Executors.newCachedThreadPool();
                setRunning(true);
                setStart(System.currentTimeMillis());

                while (isRunning()) {
                    Socket socket = serverSocket.accept();
                    executor.execute(new Client(socket, this));
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        setRunning(false);
    }

    public void clientConnect() {
        this.clients++;
    }

    public void clientDisconnect() {
        this.clients--;
    }

    // #region Getters and setters
    public Commands getCommands() {
        return commands;
    }

    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public int getClients() {
        return clients;
    }

    public void setClients(int clients) {
        this.clients = clients;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public double getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
    
    public Store getStore() {
    	return store;
    }
    
    public void setStore(String key, Value value) {
    	store.saveData(key, value);
    }
    

    // #endregion
}
