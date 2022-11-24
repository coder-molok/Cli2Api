package it.femco.cli2api.basics;

import it.femco.cli2api.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Molok
 * @version 30/09/22 01:15
 */
public class SimpleCommand implements Command {
    private final String spell;

    public SimpleCommand(String commandSpell) {
        this.spell = commandSpell;
    }

    @Override
    public Response call(String... parameters) {
        Execution execution = ExecutionFactory.getExecution(this.spell, parameters);

        return run(execution);
    }

    @Override
    public ResponseBuilder getResponseBuilder() {
        return new PlainTextResponseBuilder();
    }

    private Response run(Execution execution) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(execution.getCLICommand());
        } catch (IOException e) {
            // todo: append in the Response Error
            execution.fails(e);
        }
        return getResponseBuilder().build(execution, process);
    }

    public static BufferedReader getResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader;
    }
}
