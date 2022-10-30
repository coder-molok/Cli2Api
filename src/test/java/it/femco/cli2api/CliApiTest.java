package it.femco.cli2api;

import it.femco.cli2api.basics.SimpleCommand;
import it.femco.cli2api.basics.TokenizedCommand;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Molok
 * @version 30/09/22 00:41
 */
public class CliApiTest extends TestCase {
    public void testSimpleCall() {
        // simulate a ECHO command
        String spell = "echo";
        Command cmd = new SimpleCommand(spell);

        assertNotNull(cmd);

        String parameter = "text to repeat";
        Response resp = cmd.call(parameter);

        assertNotNull(resp);
        assertNotNull(resp.Execution());
        assertEquals(spell, resp.Execution().getSpell());
        assertEquals(1, resp.Execution().getParameters().length);
        assertEquals(parameter, resp.Execution().getParameters()[0]);
        assertTrue(resp.Execution().isCompleted());
        assertTrue(resp.Execution().isSuccess());
        assertNotNull(resp.Output());
        assertEquals(parameter+"\n", resp.Output().GetRaw());
    }
    public void testTokenizedCall() {
        // simulate a ECHO command
        String spell = "echo";
        Command cmd = new TokenizedCommand(spell);

        assertNotNull(cmd);

        String parameter = "text to repeat";
        Response resp = cmd.call(parameter);

        assertNotNull(resp);
        assertNotNull(resp.Execution());
        assertEquals(spell, resp.Execution().getSpell());
        assertEquals(1, resp.Execution().getParameters().length);
        assertEquals(parameter, resp.Execution().getParameters()[0]);
        assertTrue(resp.Execution().isCompleted());
        assertTrue(resp.Execution().isSuccess());
        assertNotNull(resp.Output());
        assertEquals(parameter+"\n", resp.Output().GetRaw());
        assertFalse(resp.Output().keySet().isEmpty());
        assertTrue(resp.Output().containsKey(TokenizedCommand.TOKENS_COUNT));
        assertEquals(3, resp.Output().getOrDefault(TokenizedCommand.TOKENS_COUNT, -1));
        assertEquals("text", resp.Output().getOrDefault("0",null));
        assertEquals("to", resp.Output().getOrDefault("1",null));
        assertEquals("repeat", resp.Output().getOrDefault("2",null));
        assertFalse(resp.Output().containsKey("3"));
    }
}
