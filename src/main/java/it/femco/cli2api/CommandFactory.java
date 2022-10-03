package it.femco.cli2api;

/**
 * @author Molok
 * @version 30/09/22 01:05
 */
public class CommandFactory {
    public static Command buildCommand(String commandSpell) {
        return new CommandBase(commandSpell);
    }
}
