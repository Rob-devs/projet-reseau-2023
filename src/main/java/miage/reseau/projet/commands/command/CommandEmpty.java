package miage.reseau.projet.commands.command;

import miage.reseau.projet.resp.ContentRESP;
import miage.reseau.projet.resp.TypeRESP;
import miage.reseau.projet.utils.LogBuilder;

/**
 * The CommandEmpty class is a Java implementation of the Command interface with methods for executing
 * and matching content, returning an error message if no command is found.
 */
public class CommandEmpty implements Command {

    @Override
    public ContentRESP execute(ContentRESP content) {
        return new ContentRESP(TypeRESP.ERROR, LogBuilder.getNoCmdFoundMessage());
    }

    @Override
    public boolean matches(ContentRESP content) {
        return false;
    }
}
