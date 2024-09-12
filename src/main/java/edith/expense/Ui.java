package edith.expense;

import edith.Parser;
import edith.expense.exception.InvalidExpenseIndexException;
import edith.expense.exception.MissingExpenseAmountException;
import edith.expense.exception.MissingExpenseDetailsException;
import edith.expense.exception.MissingExpenseIndexException;
import edith.expense.exception.MissingExpenseTagException;

/**
 * Handles user input concerning expenses.
 */
public class Ui {

    /**
     * Adds expense to list of expenses.
     * Command for add expense is expense add [expense name] [expense amount].
     * @param userInput User input.
     * @param expenses List of expenses.
     * @return Chatbot's reply indicating success or error.
     */
    public static String addExpense(String userInput, Expenses expenses) {
        try {
            String expenseDetails = Parser.getExpenseDetails(userInput);
            String expenseName = Parser.getExpenseName(expenseDetails);
            double expenseAmount = Parser.getExpenseAmount(expenseDetails);
            Expense expense = new Expense(expenseName, expenseAmount);
            expenses.addExpense(expense);
            return "added this expense to your expenses: " + expense;
        } catch (MissingExpenseDetailsException | MissingExpenseAmountException e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes an expense from list of expenses.
     * Command for delete expense is expense delete [expense index].
     * @param userInput User input.
     * @param expenses List of expenses.
     * @return Chatbot's reply indicating success or error.
     */
    public static String deleteExpense(String userInput, Expenses expenses) {
        try {
            int expenseNumber = Parser.getExpenseNumber(userInput);
            String expenseToBeDeleted = expenses.getExpense(expenseNumber).toString();
            expenses.deleteExpense(expenseNumber);
            return "i've deleted this expense: " + expenseToBeDeleted;
        } catch (MissingExpenseIndexException | InvalidExpenseIndexException e) {
            return e.getMessage();
        }
    }

    /**
     * Tags an expense.
     * @param userInput User input.
     * @param expenses List of expenses.
     * @return Chatbot's reply indicating success or error.
     */
    public static String tagExpense(String userInput, Expenses expenses) {
        try {
            int expenseNumber = Parser.getExpenseNumber(userInput);
            String tag = Parser.getExpenseTag(userInput);
            expenses.tagExpense(expenseNumber, tag);
            return "nice! i've tagged this expense: " + expenses.getExpense(expenseNumber);
        } catch (MissingExpenseIndexException | MissingExpenseTagException | InvalidExpenseIndexException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns an overview of expenses.
     * @param expenses List of expenses.
     * @return Chatbot's reply indicating success or error.
     */
    public static String viewExpenseOverview(Expenses expenses) {
        return expenses.getOverview();
    }

    /**
     * Returns a list of expenses
     * @param expenses List of expenses
     * @return Chatbot's reply indicating success of error.
     */
    public static String showListOfExpenses(Expenses expenses) {
        return "here's a list of your expenses so far:\n" + expenses;
    }
}
