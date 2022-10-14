package it.femco.cli2api;

import junit.framework.TestCase;

/**
 * @author Molok
 * @version 30/09/22 00:41
 */
public class CliApiTest extends TestCase {
    public void testSimpleCall() {
        // simulate a ECHO command
        String spell = "echo";
        Command cmd = new CommandBase(spell);

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
    }

    private class TokenizedCommand extends CommandBase implements Command {
        public TokenizedCommand(String commandSpell) {
            super(commandSpell);
        }
    }
}
