package edith.task.exception;

/**
 * This class extends Exception class. It is thrown when the user does not input a deadline after naming the task.
 */
public class MissingDeadlineException extends Exception {
    /**
     * Constructor for MissingDeadlineException
     */
    public MissingDeadlineException() {
        super("""
                oops! deadline cannot be empty! please enter a deadline after task type and task name. for example:

                      deadline cs2100 quiz /by 2pm
                
                for a full list of commands, send command.
                """);
    }
}
