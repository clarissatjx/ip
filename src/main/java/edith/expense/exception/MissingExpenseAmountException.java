package edith.expense.exception;

/**
 * Exception to handle missing expense amount in user input.
 */
public class MissingExpenseAmountException extends Exception {
    /**
     * Constructor.
     */
    public MissingExpenseAmountException() {
        super("""
                oops! you are missing the expense name. please specify the expense name and amount. for example:
                
                       expense add bus fare 4.2
                
                for a full list of commands, send command.
                """);
    }
}
