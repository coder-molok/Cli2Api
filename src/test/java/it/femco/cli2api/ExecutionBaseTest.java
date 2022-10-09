package it.femco.cli2api;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * @author Molok
 * @version 07/10/22 00:04
 */
public class ExecutionBaseTest extends TestCase {

    public void testGetCLICommand() {
        String spell = "abra";
        String[] parameters = {"cadabra","vim","salabim"};
        ExecutionBase testObj = new ExecutionBase(spell, parameters);

        assertEquals(spell, testObj.getCLICommand()[0]);
        assertEquals(String.join(",",spell, parameters[0], parameters[1], parameters[2]),
                String.join(",", testObj.getCLICommand()));
    }
}