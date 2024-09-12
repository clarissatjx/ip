package edith;

import edith.expense.Expenses;
import edith.task.ToDoList;

/**
 * This class is the entry point for chatbot Edith.
 */
public class Edith {
    private static final ToDoList TO_DO_LIST = new ToDoList();
    private static final Expenses EXPENSES = new Expenses();

    public String getResponse(String input) {
        return Ui.handleUserInput(input, TO_DO_LIST, EXPENSES);
    }

    /**
     * Loads data from user's hard drive: todolist and expenses.
     */
    public void loadData() {
        Storage.loadTasks(TO_DO_LIST);
        Storage.loadExpenses(EXPENSES);
    }
}
