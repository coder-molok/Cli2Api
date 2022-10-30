package it.femco.cli2api;

import junit.framework.TestCase;

/**
 * @author Molok
 * @version 07/10/22 00:04
 */
public class ExecutionImplTest extends TestCase {

    public void testGetCLICommand() {
        String spell = "abra";
        String[] parameters = {"cadabra","vim","salabim"};
        ExecutionImpl testObj = new ExecutionImpl(spell, parameters);

        assertEquals(spell, testObj.getCLICommand()[0]);
        assertEquals(String.join(",",spell, parameters[0], parameters[1], parameters[2]),
                String.join(",", testObj.getCLICommand()));
    }
}