package edith.task.exception;

/**
 * This class extends the Exception class. It is thrown when user input is missing a keyword.
 */
public class MissingKeywordException extends Exception {
    /**
     * Constructor for MissingKeywordException
     */
    public MissingKeywordException() {
        super("""
                oops! you are missing a keyword. please use this format: find XXX. for example:

                      find book
                      
                for a full list of commands, send command.
                """);
    }
}
