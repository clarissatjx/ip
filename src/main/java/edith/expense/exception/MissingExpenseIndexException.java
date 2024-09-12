package edith.expense.exception;

/**
 * Exception class for missing expense index in user input.
 */
public class MissingExpenseIndexException extends Exception {
    /**
     * Constructor.
     */
    public MissingExpenseIndexException() {
        super("""
                oops! you are missing the expense index. please specify the expense index. for example:
                                
                       expense delete 1
                                
                for a full list of commands, send command.""");

    }
}
