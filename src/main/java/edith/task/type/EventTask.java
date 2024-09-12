package edith.task.type;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class extends the Task class. The EventTask inherits the following fields: String taskName,
 * boolean isCompleted. It also has its own field: String start, String end.
 */
public class EventTask extends Task {
    private String start;
    private String end;

    /**
     * Constructor for EventTask class.
     * @param taskName Name of task.
     * @param start Start date/time of task.
     * @param end End date/time of task.
     */
    public EventTask(String taskName, String start, String end) {
        super(taskName);
        try {
            LocalDateTime startInDateTimeFormat = LocalDateTime.parse(start);
            this.start = startInDateTimeFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate startInDateFormat = LocalDate.parse(start);
                this.start = startInDateFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            } catch (DateTimeParseException e1) {
                try {
                    LocalTime startInTimeFormat = LocalTime.parse(start);
                    this.start = startInTimeFormat.format(DateTimeFormatter.ofPattern("h:mma"));
                } catch (DateTimeParseException e2) {
                    this.start = start;
                }
            }
        }

        try {
            LocalDateTime endInDateTimeFormat = LocalDateTime.parse(end);
            this.end = endInDateTimeFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
        } catch (DateTimeParseException e) {
            try {
                LocalDate endInDateFormat = LocalDate.parse(end);
                this.end = endInDateFormat.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            } catch (DateTimeParseException e1) {
                try {
                    LocalTime endInTimeFormat = LocalTime.parse(end);
                    this.end = endInTimeFormat.format(DateTimeFormatter.ofPattern("h:mma"));
                } catch (DateTimeParseException e2) {
                    this.end = end;
                }
            }
        }
    }

    @Override
    public String convertToFileFormat() {
        String divider = " | ";
        String status = this.getCompletionStatus() ? "1" : "0";
        return "E" + divider + status + divider + this.getTaskName() + divider + this.start + divider + this.end;
    }

    @Override
    public String toString() {
        if (this.getCompletionStatus()) {
            return "[E][X] " + this.getTaskName() + " (from: " + this.start + " to: " + this.end + ")";
        } else {
            return "[E][ ] " + this.getTaskName() + " (from: " + this.start + " to: " + this.end + ")";
        }
    }
}
