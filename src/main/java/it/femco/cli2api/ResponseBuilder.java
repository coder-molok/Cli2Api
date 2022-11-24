package it.femco.cli2api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Molok
 * @version 16/10/22 22:04
 */
public interface ResponseBuilder {
    static String getAvailableOutput(Process process) {
        try {
            if (process.getInputStream().available()>0) {
                byte[] buffer = process.getInputStream().readAllBytes();
                return new String(buffer, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            // todo: append in the Response Error
            throw new RuntimeException(e);
        }
        return null;
    }
    static void appendIfNotNull(String newText, StringBuffer collector) {
        if (newText != null) {
            collector.append(newText);
        }
    }

    default Response build(Execution execution, Process process) {
        if (process != null && execution.isSuccess()) {
            return this.buildResponse(execution, process);
        }
        Map<String, Object> errorOutput;
        if (!execution.isSuccess()) {
            errorOutput = execution.getCliError();
        } else {
            errorOutput = Map.of(
                    CliError.ERROR_CODE, 0,
                    CliError.ERROR_MSG, "No process instance.",
                    CliError.ERROR_EXCEPTION, new NullPointerException("Process is null")
            );
        }
        return new Response(execution, new Output(null, errorOutput));
    }
    Response buildResponse(Execution execution, Process process);
}
