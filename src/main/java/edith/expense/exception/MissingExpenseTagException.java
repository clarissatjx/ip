package edith.expense.exception;

/**
 * Exception class for missing expense tag in user input.
 */
public class MissingExpenseTagException extends Exception {
    /**
     * Constructor.
     */
    public MissingExpenseTagException() {
        super("""
                oops! you are missing the expense tag. please specify the expense tag. for example:
                to tag expense at index 1 with 'transport',
                                
                       expense tag 1 transport
                                
                for a full list of commands, send command.""");

    }
}
