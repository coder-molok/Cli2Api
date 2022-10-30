package it.femco.cli2api.basics;

import it.femco.cli2api.Execution;
import it.femco.cli2api.Output;
import it.femco.cli2api.Response;
import it.femco.cli2api.ResponseBuilder;

/**
 * @author Molok
 * @version 16/10/22 22:06
 */
public class PlainTextResponseBuilder implements ResponseBuilder {
    @Override
    public Response buildResponse(Execution execution, Process process) {
        StringBuffer outputText = new StringBuffer();
        Output output = new Output(outputText);
        while (process.isAlive()) {
            ResponseBuilder.appendIfNotNull(
                    ResponseBuilder.getAvailableOutput(process),
                    outputText);
        }
        ResponseBuilder.appendIfNotNull(
                ResponseBuilder.getAvailableOutput(process),
                outputText);
        return new Response(execution, output);
    }
}
