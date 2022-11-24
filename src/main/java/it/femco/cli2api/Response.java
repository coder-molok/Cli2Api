package it.femco.cli2api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Molok
 * @version 30/09/22 01:18
 */
public class Response {
    private final Execution execution;
    private final Output output;

    private String DEFAULT_ERROR_MESSAGE = "Unknown error";

    public Response(Execution execution, Output output) {
        this.execution = execution;
        this.output = output;
    }

    public Execution Execution() {
        return this.execution;
    }

    public Output Output() {
        return this.output;
    }

    public CliError Error() {
        if (execution.isSuccess()) return null;
        return new CliError(Integer.parseInt((String)output.getOrDefault(CliError.ERROR_CODE, -1)),
                (String)output.getOrDefault(CliError.ERROR_MSG,DEFAULT_ERROR_MESSAGE),
                (Throwable)output.getOrDefault(CliError.ERROR_EXCEPTION, null));
    }
}
