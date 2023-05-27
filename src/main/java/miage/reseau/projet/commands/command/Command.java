package miage.reseau.projet.commands.command;

import miage.reseau.projet.resp.ContentRESP;

// This is a Java interface defining a Command object. It has two methods: `execute` which takes a
// `ContentRESP` object as input and returns a `ContentRESP` object as output, and `matches` which
// takes a `ContentRESP` object as input and returns a boolean indicating whether the input matches the
// command. This interface can be implemented by different classes to define different types of
// commands.
public interface Command {

    /**
     * Executes the command for a given request.
     *
     * @param request the request
     * @return the response to send to the client
     */
    public ContentRESP execute(ContentRESP request);

    /**
     * Checks if a request matches the current command.
     *
     * @param request the request
     * @return true if the request matches the command
     */
    public boolean matches(ContentRESP request);
}
