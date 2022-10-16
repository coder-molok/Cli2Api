package it.femco.cli2api;

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
            outputText.append(ResponseBuilder.getAvailableOutput(process));
        }
        outputText.append(ResponseBuilder.getAvailableOutput(process));
        return new Response(execution, output);
    }
}
