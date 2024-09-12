package edith.task.exception;

/**
 * This class extends the Exception class. It is thrown when the user does not input the event duration (/from or /to)
 * after naming the task.
 */
public class MissingEventDurationException extends Exception {
    /**
     * Constructor for MissingEventDurationException
     */
    public MissingEventDurationException() {
        super("""
                oops! event duration cannot be empty. please enter a duration after the task type and name. for example:

                      event cs2101 project meeting /from 4pm /to 7pm
                
                for a full list of commands, send command.
                """);
    }
}
