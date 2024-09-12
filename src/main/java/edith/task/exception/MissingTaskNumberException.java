package edith.task.exception;

/**
 * This class extends the Exception class. It is thrown when the user does not specify the task number.
 */
public class MissingTaskNumberException extends Exception {
    /**
     * Constructor for MissingTaskNumberException
     */
    public MissingTaskNumberException() {
        super("""
                oops! you are missing a task number. please use the correct format. for example:

                      mark 3
                
                for a full list of commands, send command.
                """);
    }
}
