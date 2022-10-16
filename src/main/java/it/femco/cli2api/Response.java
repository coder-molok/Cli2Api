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
}
