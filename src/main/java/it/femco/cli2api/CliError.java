package it.femco.cli2api;

/**
 * @author Molok
 * @version 20/11/22 22:48
 */
public class CliError extends Error {
    private int errorCode;
    public static String ERROR_CODE = "-ErrorCode-";
    public static String ERROR_MSG = "-ErrorMessage-";
    public static String ERROR_EXCEPTION = "-ErrorException-";

    public CliError(int code, String message, Throwable cause) {
        super(message, cause);
        errorCode = code;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
