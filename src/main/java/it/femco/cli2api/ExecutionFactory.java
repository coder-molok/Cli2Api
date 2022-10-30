package it.femco.cli2api;

/**
 * @author Molok
 * @version 03/10/22 13:52
 */
public class ExecutionFactory {
    public static Execution getExecution(String commandSpell, String[] parameters) {
        return new ExecutionImpl(commandSpell, parameters);
    }
}
