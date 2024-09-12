package edith.exception;

/**
 * This class extends Exception class. It is thrown when the command given by the user is invalid
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor.
     */
    public InvalidCommandException() {
        super("""
                idk what you're saying :( please enter a valid command.
                for a full list of commands, send command""");
    }
}
