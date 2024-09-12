package edith.expense;

import java.util.ArrayList;

import edith.Storage;
import edith.expense.exception.InvalidExpenseIndexException;

/**
 * Class to store an ArrayList of Expense.
 */
public class Expenses {
    private ArrayList<Expense> expenses = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructor for Expenses class. Adds a default tag "misc" to array of tags.
     */
    public Expenses() {
        tags.add("misc");
    }

    /**
     * Adds an expense to list of expenses.
     * @param expense Expense.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
        Storage.saveExpenses(expenses);
    }

    /**
     * Returns expense at its designated index in the list.
     * @param expenseNumber Index of expense.
     * @return Expense at index i.
     */
    public Expense getExpense(int expenseNumber) throws InvalidExpenseIndexException {
        if (expenseNumber < 1 || expenseNumber > expenses.size()) {
            throw new InvalidExpenseIndexException();
        }
        return expenses.get(expenseNumber - 1);
    }

    /**
     * Deletes an expense at its designated index in the list.
     * @param expenseNumber Index of expense to be deleted.
     */
    public void deleteExpense(int expenseNumber) throws InvalidExpenseIndexException {
        if (expenseNumber < 1 || expenseNumber > expenses.size()) {
            throw new InvalidExpenseIndexException();
        }
        expenses.remove(expenseNumber - 1);
        Storage.saveExpenses(expenses);
    }

    /**
     * Returns total expenditure.
     * @return Total expenditure (in $).
     */
    public double getTotalExpenditure() {
        double total = 0;
        for (Expense expense : expenses) {
            total = total + expense.getExpenseAmount();
        }
        return total;
    }

    /**
     * Returns total expenditure by tag.
     * @param tag Expense's tag.
     * @return Total expenditure by tag (in $).
     */
    public double getExpenditureByTag(String tag) {
        double total = 0;
        if (!tags.contains(tag)) {
            return 0;
        } else {
            for (Expense expense : expenses) {
                if (expense.doesTagMatch(tag)) {
                    total = total + expense.getExpenseAmount();
                }
            }
        }
        return total;
    }

    /**
     * Tags an expense, then adds tag to list of tags if it is a new tag.
     * @param expenseNumber Index of expense.
     * @param tag Tag to be tagged to expense.
     */
    public void tagExpense(int expenseNumber, String tag) throws InvalidExpenseIndexException {
        if (expenseNumber < 1 || expenseNumber > expenses.size()) {
            throw new InvalidExpenseIndexException();
        }
        Expense expense = getExpense(expenseNumber);
        expense.changeTag(tag);
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
        Storage.saveExpenses(expenses);
    }

    /**
     * Returns an overview of user's expenditure, broken down into each tag's expenditure and total expenditure.
     * @return Overview of user's expenditure.
     */
    public String getOverview() {
        String headerMessage = "here is an overview of your expenses.\n\n";
        String totalExpenditureMessage = "total: $" + getTotalExpenditure();
        String expenditureByTagMessage = "";
        for (String tag : tags) {
            double expenditureByTag = getExpenditureByTag(tag);
            expenditureByTagMessage = expenditureByTagMessage + tag + ": $" + expenditureByTag + " \n";
        }
        return headerMessage + expenditureByTagMessage + "\n" + totalExpenditureMessage;
    }

    /**
     * Converts expenses into a string.
     * @return String to expenses.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < expenses.size(); i++) {
            str = str + (i + 1) + ". " + expenses.get(i) + "\n";
        }
        return str;
    }
}
