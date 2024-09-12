package edith.expense.exception;

/**
 * Exception class for missing expense name in user input.
 */
public class MissingExpenseDetailsException extends Exception {
    /**
     * Constructor.
     */
    public MissingExpenseDetailsException() {
        super("""
                oops! you are missing the expense name and/or amount. please specify the expense name and amount.
                for example:
                
                       expense add bus fare 4.2
                
                for a full list of commands, send command.
                """);
    }
}
