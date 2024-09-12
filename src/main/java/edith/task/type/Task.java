package edith.task.type;

/**
 * Abstract class Task. This abstract class provides methods to check and uncheck the task, get the task name and
 * status, and convert the task to file format to be saved on user's hard drive.
 */
public abstract class Task {
    private String taskName;
    private boolean isCompleted; // false for undone, true for done

    /**
     * Constructor for class Task. Sets completion status to default false.
     * @param taskName Name of task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Marks task as completed.
     */
    public void check() { // mark task as completed
        this.isCompleted = true;
    }

    /**
     * Un-marks task as completed.
     */
    public void uncheck() { // unmark task as completed
        this.isCompleted = false;
    }

    /**
     * Getter for task name.
     * @return Name of task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Getter for status of task.
     * @return Task status as a boolean.
     */
    public boolean getCompletionStatus() {
        return this.isCompleted;
    }

    /**
     * Returns a boolean for if the task name contains keyword provided in user input.
     * @param keyword User input to be checked against task name.
     * @return True if task name contains keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return taskName.contains(keyword);
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }

    /**
     * Converts each task into a string to be stored in a file.
     * @return Task to be saved in file format as a String.
     */
    public abstract String convertToFileFormat();
}
