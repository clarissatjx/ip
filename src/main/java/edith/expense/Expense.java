package edith.expense;

import java.util.Objects;

/**
 * The Expense class encapsulates an expense. It has fields to store expense name, amount (in $) and
 * tag to sort the expense
 */
public class Expense {
    private String expenseName;
    private double expenseAmount;
    private String tag;

    /**
     * Constructor for Expense class.
     * @param expenseName Name of expense.
     * @param expenseAmount Amount (in $).
     */
    public Expense(String expenseName, double expenseAmount) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        tag = "misc";
    }

    /**
     * Changes tag of expense.
     * @param tag Tag.
     */
    public void changeTag(String tag) {
        this.tag = tag;
    }

    /**
     * Getter for expense amount.
     * @return Expense amount in $.
     */
    public double getExpenseAmount() {
        return this.expenseAmount;
    }

    /**
     * Checks if tag input equals expense's tag.
     * @param tag Tag to be checked against.
     * @return True if tags are equal, false otherwise.
     */
    public boolean doesTagMatch(String tag) {
        return Objects.equals(this.tag, tag);
    }

    /**
     * Converts each expense into a string to be stored in a file.
     * @return Expense to be saved in file format as a String.
     */
    public String convertToFileFormat() {
        String divider = " | ";
        return expenseName + divider + expenseAmount + divider + tag;
    }

    @Override
    public String toString() {
        return "[" + tag + "] " + this.expenseName + " $" + expenseAmount;
    }
}
