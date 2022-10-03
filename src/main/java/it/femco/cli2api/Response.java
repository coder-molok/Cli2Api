package it.femco.cli2api;

import java.io.StringReader;

/**
 * @author Molok
 * @version 30/09/22 01:18
 */
public interface Response {
    Execution Execution();

    Output Output();
}
