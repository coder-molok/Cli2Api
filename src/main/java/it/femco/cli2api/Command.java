package it.femco.cli2api;

/**
 * @author Molok
 * @version 30/09/22 01:02
 *
 * Main representation of a CLI Software Solution.
 */
public interface Command {
    Response call(String... parameters);
}
