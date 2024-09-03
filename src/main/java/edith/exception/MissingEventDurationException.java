package edith.exception;

/**
 * This class extends the Exception class. It is thrown when the user does not input the event duration (/from or /to)
 * after naming the task.
 */
public class MissingEventDurationException extends Exception {
    public MissingEventDurationException() {
        super(" oops! event duration cannot be empty! please enter a duration after the task type and task name." +
                " for example:\n      event cs2101 project meeting /from 4pm /to 7pm");
    }
}
