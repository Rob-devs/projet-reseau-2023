package miage.reseau.projet.commands.command;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.utils.LogBuilder;

/**
 * The `Commands` class manages a list of available commands, adds new commands
 * to the list,
 * initializes the commands, executes a command based on a request, and returns
 * a matching command
 * object or a `CommandEmpty` object if no match is found.
 */
public class Commands {
    private final ArrayList<Command> commands;

    public Commands() {
        commands = new ArrayList<Command>(0);
    }

    /**
     * The function initializes commands by accepting a consumer that takes in a
     * Commands object.
     * 
     * @param consumer A functional interface that takes an object of type Commands
     *                 and returns no
     *                 result (void). It is used to initialize the commands by
     *                 accepting the current instance of the
     *                 Commands class.
     */
    public void initializeCommands(Consumer<Commands> consumer) {
        consumer.accept(this);
    }

    /**
     * This function executes a command based on a request and returns the response.
     * 
     * @param request The request parameter is an object of type ContentRESP that
     *                contains the
     *                necessary information for executing a command. It is passed as
     *                an argument to the executeCommand
     *                method.
     * @return The method `executeCommand` is returning the result of calling the
     *         `execute` method on
     *         the command object obtained from the request. The type of the
     *         returned object is `ContentRESP`.
     */
    public ContentRESP executeCommand(ContentRESP request) {
        return getCommandFromRequest(request).execute(request);
    }

    /**
     * This function returns a Command object that matches the given ContentRESP
     * request, or a
     * CommandEmpty object if no match is found.
     * 
     * @param request The parameter "request" is of type "ContentRESP", which is
     *                likely a custom class
     *                representing a request for a command to be executed.
     * @return The method `getCommandFromRequest` returns a `Command` object. If a
     *         matching command is
     *         found in the `commands` list based on the `matches` method of the
     *         `Command` class, that command
     *         is returned. Otherwise, a new instance of `CommandEmpty` is returned.
     */
    private Command getCommandFromRequest(ContentRESP request) {
        Optional<Command> matchingCommand = commands.stream()
                .filter(command -> command.matches(request))
                .findFirst();

        return matchingCommand.orElse(new CommandEmpty());
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
