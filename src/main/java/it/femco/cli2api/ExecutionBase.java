package it.femco.cli2api;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Molok
 * @version 03/10/22 13:53
 */
public class ExecutionBase implements Execution {
    private final String[] parameters;
    private String spell;
    private IOException failureCause;

    public ExecutionBase(String commandSpell, String[] parameters) {
        this.spell = commandSpell;
        this.parameters = parameters;
        this.failureCause = null;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public boolean isSuccess() {
        return this.failureCause==null;
    }

    @Override
    public String getSpell() {
        return this.spell;
    }

    @Override
    public String[] getParameters() {
        return parameters;
    }

    @Override
    public void fails(IOException e) {
        this.failureCause = e;
    }

    @Override
    public String[] getCLICommand() {
        String[] cmd = new String[this.parameters+1];
        cmd[0] = this.spell;
        Arrays.setAll(cmd, (int i) -> i==0?thi);
    }
}
