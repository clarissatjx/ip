package edith;

import static edith.expense.Ui.addExpense;
import static edith.expense.Ui.deleteExpense;
import static edith.expense.Ui.showListOfExpenses;
import static edith.expense.Ui.tagExpense;
import static edith.expense.Ui.viewExpenseOverview;
import static edith.task.Ui.addTask;
import static edith.task.Ui.changeTaskStatus;
import static edith.task.Ui.delete;
import static edith.task.Ui.find;
import static edith.task.Ui.printList;

import edith.exception.InvalidCommandException;
import edith.expense.Expenses;
import edith.expense.exception.MissingExpenseCommandException;
import edith.task.ToDoList;

/**
 * This class handles all user inputs.
 */
public class Ui {
    private static final String GREETING = """
            hi im edith! i'm a chatbot that handles tasks and expenses.
            """;
    private static final String COMMANDS = """
            here are a list of commands i support:
            command - shows a list of commands i support
            list - shows your current todo list
            todo <task name> - adds a todo task
            deadline <task name> /by <deadline> - adds a deadline task
            event <task name> /from <event start> /to <event end> - adds an event task
            mark <task number> - marks task at its index
            unmark <task number> - unmarks task at its index
            delete <task number> - deletes task at its index
            find <keywords> - finds tasks matching keyword
            expense list - shows your current expenses
            expense overview - shows your expenses broken down into tags and totals
            expense add <expense name> <expense amount> - adds expense
            expense delete <expense index> - deletes expense at its index
            expense tag <expense index> <tag> - tags expense at its index with tag input
            """;
    private static final String FAREWELL = "bye!! see you soon love <3";

    /**
     * Handles user input accordingly. List of commands: list, mark, unmark, todo, deadline, event, delete. Prints
     * error message if user input is an invalid command.
     * @param userInput User input.
     * @param toDoList User's to-do list.
     * @return Chatbot response.
     */
    public static String handleUserInput(String userInput, ToDoList toDoList, Expenses expenses) {
        Parser.Command command = Parser.getCommand(userInput);
        switch (command) {
        case COMMAND:
            return showCommands();
        case LIST:
            return printList(toDoList);
        case MARK:
        case UNMARK:
            return changeTaskStatus(userInput, toDoList);
        case TODO:
        case DEADLINE:
        case EVENT:
            return addTask(userInput, toDoList);
        case DELETE:
            return delete(userInput, toDoList);
        case FIND:
            return find(userInput, toDoList);
        case BYE:
            return bidFarewell();
        case EXPENSE: {
            return handleExpenseUserInput(userInput, expenses);
        }
        case INVALID:
        default:
            try {
                otherCommand();
            } catch (InvalidCommandException e) {
                showError(e.getMessage());
                return e.getMessage();
            }
            break;
        }
        return "Invalid input. Please try again.";
    }

    /**
     * Handles user input relating to expenses.
     * @param userInput User input.
     * @param expenses List of expenses
     * @return Chatbot's reply.
     */
    public static String handleExpenseUserInput(String userInput, Expenses expenses) {
        try {
            Parser.ExpenseCommand expenseCommand = Parser.getExpenseCommand(userInput);
            switch (expenseCommand) {
            case ADD:
                return addExpense(userInput, expenses);
            case DELETE:
                return deleteExpense(userInput, expenses);
            case TAG:
                return tagExpense(userInput, expenses);
            case OVERVIEW:
                return viewExpenseOverview(expenses);
            case LIST:
                return showListOfExpenses(expenses);
            case INVALID:
            default:
                try {
                    otherCommand();
                } catch (InvalidCommandException e) {
                    showError(e.getMessage());
                    return e.getMessage();
                }
                break;
            }
            return "Invalid input. Please try again.";
        } catch (MissingExpenseCommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Greets user when Edith first starts up.
     * @return Greeting when Edith first starts up.
     */
    public static String greetUser() {
        return GREETING + "\n" + COMMANDS;
    }

    /**
     * Bids farewell when user inputs "bye".
     * @return Farewell message when user inputs "bye".
     */
    public static String bidFarewell() {
        return FAREWELL;
    }

    /**
     * Shows a list of commands edith supports.
     * @return Commands.
     */
    public static String showCommands() {
        return COMMANDS;
    }

    /**
     * Handles input of invalid commands.
     * @throws InvalidCommandException When user input cannot be recognised.
     */
    public static void otherCommand() throws InvalidCommandException {
        throw new InvalidCommandException();
    }


    /**
     * Prints error message from exception thrown.
     * @param errorMessage Error message from exception thrown.
     */
    public static void showError(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }
}
