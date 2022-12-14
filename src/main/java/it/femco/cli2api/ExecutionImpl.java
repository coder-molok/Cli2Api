package it.femco.cli2api;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Molok
 * @version 03/10/22 13:53
 */
public class ExecutionImpl implements Execution {
    private final String[] parameters;
    private String spell;
    private IOException failureCause;

    public ExecutionImpl(String commandSpell, String[] parameters) {
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
        String[] cmd = new String[this.parameters.length+1];
        Arrays.setAll(cmd, (int i) -> i==0?this.spell:this.parameters[i-1]);
        return cmd;
    }

    @Override
    public HashMap<String, Object> getCliError() {
        Pattern errorNum = Pattern.compile("(?<msgbefore>.* )error=(?<code>\\d+)(?<msgafter>\\D.*)?");
        Matcher match = errorNum.matcher(failureCause.getMessage());
        match.matches();
        return new HashMap<>(Map.of(
                CliError.ERROR_CODE, match.group("code"),
                CliError.ERROR_MSG, match.group("msgbefore")+match.group("msgafter"),
                CliError.ERROR_EXCEPTION, failureCause
        ));
    }
}
