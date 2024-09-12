package edith.task;

import java.util.ArrayList;

import edith.Storage;
import edith.task.exception.InvalidTaskNumberException;
import edith.task.type.Task;

/**
 * This class stores tasks as an ArrayList.
 */
public class ToDoList {
    private ArrayList<Task> toDoList = new ArrayList<>();

    /**
     * Adds a task to the list.
     * @param task Task to be added.
     */
    public void add(Task task) {
        toDoList.add(task);
        Storage.saveTasks(toDoList);
    }

    /**
     * Marks specified task as completed.
     * @param taskNumber Task-to-be-marked's number
     * @throws InvalidTaskNumberException When task number specified is < 1 or > number of current tasks in list.
     */
    public void markTaskAsCompleted(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.get(taskNumber - 1).check();
        Storage.saveTasks(toDoList);
    }

    /**
     * Un-marks specified task as uncompleted.
     * @param taskNumber Task-to-be-unmarked's number
     * @throws InvalidTaskNumberException When task number specified is < 1 or > number of current tasks in list.
     */
    public void unmarkTaskAsCompleted(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.get(taskNumber - 1).uncheck();
        Storage.saveTasks(toDoList);
    }

    /**
     * Getter for specified task.
     * @param taskNumber Task number.
     * @return Task at specified number in a string.
     * @throws InvalidTaskNumberException When task number specified is < 1 or > number of current tasks in list.
     */
    public String getTask(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        return toDoList.get(taskNumber - 1).toString();
    }

    /**
     * Getter for number of tasks in list currently.
     * @return Number of tasks.
     */
    public int getNumberOfTasks() {
        return this.toDoList.size();
    }

    /**
     * Deletes task from list.
     * @param taskNumber Task number to be deleted.
     * @throws InvalidTaskNumberException When task number specified is < 1 or > number of current tasks in list.
     */
    public void delete(int taskNumber) throws InvalidTaskNumberException {
        if (taskNumber > toDoList.size() || taskNumber < 1) {
            throw new InvalidTaskNumberException();
        }
        toDoList.remove(taskNumber - 1);
        Storage.saveTasks(toDoList);
    }

    /**
     * Returns a list of Tasks matching description provided by user input.
     * @param keyword User input to be checked against task name.
     * @return List of tasks.
     */
    public ToDoList getListOfTasksMatchingKeyword(String keyword) {
        ToDoList tasksMatchingDescription = new ToDoList();
        for (Task task : toDoList) {
            if (task.containsKeyword(keyword)) {
                tasksMatchingDescription.add(task);
            }
        }
        return tasksMatchingDescription;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < toDoList.size(); i++) {
            str = str + " " + (i + 1) + "." + toDoList.get(i).toString() + "\n";
        }
        return str;
    }
}
