package edith.task.type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class extends the Task class. The DeadlineTask inherits the following fields: String taskName,
 * boolean isCompleted. It also has its own field: String deadline.
 */
public class DeadlineTask extends Task {

    private String deadline;

    /**
     * Constructor for DeadlineTask class.
     * @param taskName Name of task.
     * @param deadline Task deadline.
     */
    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        try {
            LocalDateTime deadlineInFormat = LocalDateTime.parse(deadline);
            this.deadline = deadlineInFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate deadlineInFormat = LocalDate.parse(deadline);
                this.deadline = deadlineInFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            } catch (DateTimeParseException e1) {
                this.deadline = deadline;
            }
        }
    }

    @Override
    public String convertToFileFormat() {
        String divider = " | ";
        String status = this.getCompletionStatus() ? "1" : "0";
        return "D" + divider + status + divider + this.getTaskName() + divider + this.deadline;
    }

    @Override
    public String toString() {
        if (this.getCompletionStatus()) {
            return "[D][X] " + this.getTaskName() + " (by: " + this.deadline + ")";
        } else {
            return "[D][ ] " + this.getTaskName() + " (by: " + this.deadline + ")";
        }
    }
}
