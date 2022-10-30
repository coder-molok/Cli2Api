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
                byte[] buffer = process.getInputStream().readAllBytes();
                String buff2 = new String(buffer, StandardCharsets.UTF_8);
                return buff2;
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

    Response buildResponse(Execution execution, Process process);
}
