package edith;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import edith.expense.exception.*;
import edith.task.exception.MissingDeadlineException;
import edith.task.exception.MissingEventDurationException;
import edith.task.exception.MissingKeywordException;
import edith.task.exception.MissingTaskNameException;
import edith.task.exception.MissingTaskNumberException;

/**
 * This class handles all parsing of Strings into appropriate outputs.
 */
public class Parser {
    /**
     * Class for commands.
     */
    public enum Command {
        COMMAND,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        BYE,
        EXPENSE,
        INVALID
    }

    /**
     * Class for expense commands.
     */
    enum ExpenseCommand {
        ADD,
        DELETE,
        TAG,
        OVERVIEW,
        LIST,
        INVALID
    }

    /**
     * Returns command to be executed.
     * @param userInput User input to be deciphered.
     * @return Command.
     */
    public static Command getCommand(String userInput) {
        List<String> userInputs = Arrays.asList(userInput.split(" "));
        return switch (userInputs.get(0)) {
        case "command" -> Command.COMMAND;
        case "mark" -> Command.MARK;
        case "unmark" -> Command.UNMARK;
        case "list" -> Command.LIST;
        case "todo" -> Command.TODO;
        case "deadline" -> Command.DEADLINE;
        case "event" -> Command.EVENT;
        case "delete" -> Command.DELETE;
        case "find" -> Command.FIND;
        case "bye" -> Command.BYE;
        case "expense" -> Command.EXPENSE;
        default -> Command.INVALID;
        };
    }

    /**
     * Returns expense command to be executed
     * @param userInput User input to be deciphered.
     * @return ExpenseCommand.
     */
    public static ExpenseCommand getExpenseCommand(String userInput) throws MissingExpenseCommandException {
        try {
            List<String> userInputs = Arrays.asList(userInput.split(" "));
            return switch (userInputs.get(1)) {
            case "add" -> ExpenseCommand.ADD;
            case "delete" -> ExpenseCommand.DELETE;
            case "tag" -> ExpenseCommand.TAG;
            case "list" -> ExpenseCommand.LIST;
            case "overview" -> ExpenseCommand.OVERVIEW;
            default -> ExpenseCommand.INVALID;
            };
        } catch (IndexOutOfBoundsException e) {
            throw new MissingExpenseCommandException();
        }
    }

    /**
     * Returns expense details - name and expenditure.
     * @param userInput User input.
     * @return Details of expense.
     */
    public static String getExpenseDetails(String userInput) throws MissingExpenseDetailsException {
        try {
            String[] parts = userInput.split(" ");
            StringBuilder details = new StringBuilder();
            for (int i = 2; i < parts.length; i++) {
                details.append(parts[i]);
                if (i < parts.length - 1) {
                    details.append(" ");
                }
            }
            return details.toString();
        } catch (Exception e) {
            throw new MissingExpenseDetailsException();
        }
    }

    /**
     * Returns name of expense.
     * @param expenseDetails Expense details including name and expenditure.
     * @return Name of expenditure.
     */
    public static String getExpenseName(String expenseDetails) {
        String[] parts = expenseDetails.split(" ");
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < parts.length - 1; i++) {
            name.append(parts[i]);
            if (i < parts.length - 2) {
                name.append(" ");
            }
        }
        return name.toString();
    }

    /**
     * Returns expense's amount.
     * @param expenseDetails Expense details including name and expenditure.
     * @return Expense's amount.
     */
    public static double getExpenseAmount(String expenseDetails) throws MissingExpenseAmountException {
        try {
            String[] parts = expenseDetails.split(" ");
            double amount = Double.parseDouble(parts[parts.length - 1]);
            return amount;
        } catch (Exception e) {
            throw new MissingExpenseAmountException();
        }
    }

    /**
     * Returns expense index number.
     * @param userInput User input.
     * @return Expense index number.
     */
    public static int getExpenseNumber(String userInput) throws MissingExpenseIndexException {
        try {
            String[] parts = userInput.split(" ");
            return Integer.parseInt(parts[2]);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingExpenseIndexException();
        }
    }

    /**
     * Returns tag to be tagged to an expense.
     * @param userInput User input.
     * @return Tag for expense.
     */
    public static String getExpenseTag(String userInput) throws MissingExpenseTagException {
        String[] parts = userInput.split(" ");
        StringBuilder tag = new StringBuilder();
        for (int i = 3; i < parts.length; i++) {
            tag.append(parts[i]);
            if (i < parts.length - 1) {
                tag.append(" ");
            }
        }
        if (tag.toString().isEmpty()) {
            throw new MissingExpenseTagException();
        }
        return tag.toString();
    }

    /**
     * Returns the task number based on userInput.
     * @param userInput User input to be deciphered.
     * @return task number as an integer.
     */
    public static int getTaskNumber(String userInput) throws MissingTaskNumberException {
        try {
            List<String> userInputs = Arrays.asList(userInput.split(" "));
            int taskNumber = Integer.parseInt(userInputs.get(1));
            return taskNumber;
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskNumberException();
        }
    }

    /**
     * Returns task details including task name and task deadline/duration.
     * @param userInput User input to be deciphered.
     * @param taskType Type of task (Todo, Deadline or Event).
     * @return Task details in a string.
     * @throws MissingTaskNameException When task name is not specified.
     */
    public static String getTaskDetails(String userInput, Command taskType) throws MissingTaskNameException {
        try {
            String type;
            if (taskType.equals(Command.TODO)) {
                type = "todo";
            } else if (taskType.equals(Command.DEADLINE)) {
                type = "deadline";
            } else {
                type = "event";
            }
            List<String> userInputs = Arrays.asList(userInput.split(type + " "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingTaskNameException();
        }
    }

    /**
     * Returns task name
     * @param taskDetails Details of task in a string including task name and task deadline/duration if applicable.
     * @param taskType Type of task (Todo, Deadline or Event).
     * @return Task name in a string.
     */
    public static String getTaskName(String taskDetails, String taskType) {
        List<String> userInputs;
        if (Objects.equals(taskType, "deadline")) {
            userInputs = Arrays.asList(taskDetails.split(" /by "));
        } else {
            userInputs = Arrays.asList(taskDetails.split(" /from "));
        }
        return userInputs.get(0);
    }

    /**
     * Returns task deadline (only applicable to tasks of class DeadlineTask).
     * @param taskDetails Details of task in a string including task name and task deadline.
     * @return Task deadline in a string.
     * @throws MissingDeadlineException When task deadline is not specified.
     */
    public static String getTaskDeadline(String taskDetails) throws MissingDeadlineException {
        try {
            List<String> userInputs = Arrays.asList(taskDetails.split(" /by "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingDeadlineException();
        }
    }

    /**
     * Returns task deadline (only applicable to tasks of class EventTask).
     * @param taskDetails Details of task in a string including task name and task duration.
     * @return Task duration in a string.
     * @throws MissingEventDurationException When task duration is not specified.
     */
    public static String getTaskDuration(String taskDetails) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDetails.split(" /from "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    /**
     * Returns task start date and/or time (only applicable to tasks of class EventTask).
     * @param taskDuration Duration of task in a string including start and end date/time.
     * @return Task start date and/or time in a string.
     * @throws MissingEventDurationException When task duration is not specified.
     */
    public static String getTaskStart(String taskDuration) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDuration.split(" /to "));
            return userInputs.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    /**
     * Returns task end date and/or time (only applicable to tasks of class EventTask).
     * @param taskDuration Duration of task in a string including start and end date/time.
     * @return Task end date and/or time in a string.
     * @throws MissingEventDurationException When task duration is not specified.
     */
    public static String getTaskEnd(String taskDuration) throws MissingEventDurationException {
        try {
            List<String> userInputs = Arrays.asList(taskDuration.split(" /to "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingEventDurationException();
        }
    }

    /**
     * Returns the keyword user is trying to search.
     * @param userInput User input.
     * @return keyword.
     * @throws MissingKeywordException When keyword is missing from user input.
     */
    public static String getKeyword(String userInput) throws MissingKeywordException {
        try {
            List<String> userInputs = Arrays.asList(userInput.split("find "));
            return userInputs.get(1);
        } catch (IndexOutOfBoundsException e) {
            throw new MissingKeywordException();
        }
    }
}
