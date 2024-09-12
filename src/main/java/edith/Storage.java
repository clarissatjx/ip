package edith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import edith.expense.Expense;
import edith.expense.Expenses;
import edith.task.ToDoList;
import edith.task.type.DeadlineTask;
import edith.task.type.EventTask;
import edith.task.type.Task;
import edith.task.type.ToDoTask;

/**
 * This class handles all storing and loading of tasks into user's hard drive.
 */
public class Storage {

    private static final String TASKS_FILE_PATH = "./data/edith.txt";
    private static final String EXPENSES_FILE_PATH = "./data/expenses.txt";
    private static final File DIRECTORY = new File("./data");

    /**
     * Saves list of tasks into user's hard drive whenever the list is altered.
     * @param toDoList ArrayList of Task
     */
    public static void saveTasks(ArrayList<Task> toDoList) {
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdirs();
        }

        try (FileWriter writer = new FileWriter(TASKS_FILE_PATH)) {
            for (Task task : toDoList) {
                writer.write(task.convertToFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("oops! an error occurred while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Saves list of expenses into user's hard drive whenever the list is altered.
     * @param expenses ArrayList of Expense.
     */
    public static void saveExpenses(ArrayList<Expense> expenses) {
        if (!DIRECTORY.exists()) {
            DIRECTORY.mkdirs();
        }

        try (FileWriter writer = new FileWriter(EXPENSES_FILE_PATH)) {
            for (Expense expense : expenses) {
                writer.write(expense.convertToFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("oops! an error occurred while saving expenses: " + e.getMessage());
        }
    }

    /**
     * Loads list of tasks into ToDoList when Edith starts up. Creates a directory and a file if specified file is not
     * found in user's hard drive. Prints error if the file's format cannot be deciphered.
     * @param toDoList To-do list.
     */
    public static void loadTasks(ToDoList toDoList) {
        try (Scanner scanner = new Scanner(new File(TASKS_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> properties = List.of(line.split(" \\| "));

                if (Objects.equals(properties.get(0), "T")) {
                    ToDoTask task = new ToDoTask(properties.get(2));
                    setTaskStatus(properties.get(0), task);
                    toDoList.add(task);
                } else if (Objects.equals(properties.get(0), "D")) {
                    DeadlineTask task = new DeadlineTask(properties.get(2), properties.get(3));
                    setTaskStatus(properties.get(0), task);
                    toDoList.add(task);
                } else if (Objects.equals(properties.get(0), "E")) {
                    EventTask task = new EventTask(properties.get(2), properties.get(3), properties.get(4));
                    setTaskStatus(properties.get(0), task);
                    toDoList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("psa: you currently do not have the file edith.txt under the data directory. "
                    + "i'll create one for you right now!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("oops!!! there's something wrong with edith.txt's format... please check it! for now,"
                    + "i will remove the tasks that have formatting issues.");
        }
    }

    /**
     * Sets task status. Helper function for loadTasks().
     * @param status String of either "1" or "0".
     * @param task Task to be set.
     */
    public static void setTaskStatus(String status, Task task) {
        if (Objects.equals(status, "1")) {
            task.check();
        }
    }

    /**
     * Loads list of expenses into Expenses when Edith starts up. Creates a directory and a file if specified file is
     * not found in user's hard drive. Prints error if the file's format cannot be deciphered.
     * @param expenses List of expenses.
     */
    public static void loadExpenses(Expenses expenses) {
        try (Scanner scanner = new Scanner(new File(EXPENSES_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                List<String> properties = List.of(line.split(" \\| "));
                String expenseName = properties.get(0);
                double expenseAmount = Double.parseDouble(properties.get(1));
                Expense expense = new Expense(expenseName, expenseAmount);
                if (properties.size() > 2) {
                    String expenseTag = properties.get(2);
                    expense.changeTag(expenseTag);
                }
                expenses.addExpense(expense);
            }
        } catch (FileNotFoundException e) {
            System.out.println("psa: you currently do not have the file expenses.txt under the data directory. "
                    + "i'll create one for you right now!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("oops!!! there's something wrong with expenses.txt format... please check it! for now,"
                    + "i will remove the expenses that have formatting issues.");
        }
    }
}
