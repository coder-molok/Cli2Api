package it.femco.cli2api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Molok
 * @version 16/10/22 22:04
 */
public interface ResponseBuilder {
    static String getAvailableOutput(Process process) {
        try {
            if (process.getInputStream().available()>0) {
                return new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            // todo: append in the Response Error
            throw new RuntimeException(e);
        }
        return null;
    }

    Response buildResponse(Execution execution, Process process);
}
