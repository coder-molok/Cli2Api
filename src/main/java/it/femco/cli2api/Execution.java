package it.femco.cli2api;

import java.io.IOException;

/**
 * @author Molok
 * @version 03/10/22 13:21
 */
public interface Execution {
    boolean isCompleted();

    boolean isSuccess();

    /**
     * Return the original text definition of the command.
     * @return the command spell.
     */
    String getSpell();

    /**
     * Return the set of parameters used for the execution.
     * @return a list of strings
     */
    String[] getParameters();

    void fails(IOException e);

    String[] getCLICommand();
}
