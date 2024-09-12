package edith.expense.exception;

/**
 * Exception to handle missing expense command.
 */
public class MissingExpenseCommandException extends Exception {
    /**
     * Constructor.
     */
    public MissingExpenseCommandException() {
        super("""
                oops! you are missing a command. please specify the expense command you want to execute.
                for a full list of commands, send command.""");
    }
}
