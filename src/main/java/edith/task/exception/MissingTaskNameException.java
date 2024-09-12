package edith.task.exception;

/**
 * This class extends the Exception class. It is thrown when the user does not input the name of the task.
 */
public class MissingTaskNameException extends Exception {
    /**
     * Constructor for MissingTaskNameException
     */
    public MissingTaskNameException() {
        super("""
                oops! task name cannot be empty! please enter a task name after the type of task. for example:

                      todo cs2103t quiz
                     
                for a full list of commands, send command.
                """);
    }
}
