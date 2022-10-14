package it.femco.cli2api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Molok
 * @version 30/09/22 01:15
 */
public class CommandBase implements Command {
    private final String spell;

    public CommandBase(String commandSpell) {
        this.spell = commandSpell;
    }

    @Override
    public Response call(String... parameters) {
        Execution execution = ExecutionFactory.getExecution(this.spell, parameters);

        return CommandBase.run(execution);
    }

    private static Response run(Execution execution) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(execution.getCLICommand());
        } catch (IOException e) {
            // todo: append in the Response Error
            execution.fails(e);
        }
        return new Response(execution, buildResponse(execution, process));
    }

    private static Output buildResponse(Execution execution, Process process) {
        StringBuffer outputText = new StringBuffer();
        Output output = new Output(outputText);
        while (process.isAlive()) {
            getAvailableOutput(process, outputText);
        }
        getAvailableOutput(process, outputText);
        return output;
    }

    private static void getAvailableOutput(Process process, StringBuffer outputText) {
        try {
            if (process.getInputStream().available()>0) {
                outputText.append(new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            // todo: append in the Response Error
            throw new RuntimeException(e);
        }
    }

    public static BufferedReader getResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        return reader;
    }
}
