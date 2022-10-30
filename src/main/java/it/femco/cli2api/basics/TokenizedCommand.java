package it.femco.cli2api.basics;

import it.femco.cli2api.*;

import java.util.HashMap;

/**
 * @author Molok
 * @version 30/10/22 22:48
 */
public class TokenizedCommand extends SimpleCommand implements Command, ResponseBuilder {
    public static final String TOKENS_COUNT = "_tokens_count_";
    public TokenizedCommand(String commandSpell) {
        super(commandSpell);
    }

    @Override
    public ResponseBuilder getResponseBuilder() {
        return this;
    }

    @Override
    public Response buildResponse(Execution execution, Process process) {
        StringBuffer outputText = new StringBuffer();
        HashMap<String, Object> outputMap = new HashMap<>();
        Output output = new Output(outputText, outputMap);
        while (process.isAlive()) {
            ResponseBuilder.appendIfNotNull(
                    ResponseBuilder.getAvailableOutput(process),
                    outputText
            );
        }
        ResponseBuilder.appendIfNotNull(
                ResponseBuilder.getAvailableOutput(process),
                outputText
        );
        String[] tokens = outputText.toString().split("[ \n\r]");
        int count = 0;
        for (String token : tokens) {
            outputMap.put(Integer.toString(count++), token);
        }
        outputMap.put(TOKENS_COUNT, count);
        return new Response(execution, output);
    }
}
