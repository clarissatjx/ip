package edith.expense.exception;

/**
 * Exception that handles invalid expense indices.
 */
public class InvalidExpenseIndexException extends Exception {
    /**
     * Constructor.
     */
    public InvalidExpenseIndexException() {
        super("""
                oops! thats an invalid expense index number. please try again.
                for a full list of command, send command.""");
    }
}
