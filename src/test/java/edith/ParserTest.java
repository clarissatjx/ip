package edith;

import static edith.Parser.getCommand;
import static edith.Parser.getTaskDetails;
import static edith.Parser.getTaskDuration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import edith.task.exception.MissingEventDurationException;
import edith.task.exception.MissingTaskNameException;

public class ParserTest {

    @Test
    public void testGetTaskDetails() {
        try {
            String userInput = "event project meeting /from 6pm /to 3pm";
            assertEquals("project meeting /from 6pm /to 3pm",
                    getTaskDetails(userInput, getCommand(userInput)));
        } catch (MissingTaskNameException e) {
            // should not reach this stage
            fail();
        }
    }

    @Test
    public void testGetTaskEnd_exceptionThrown() {
        try {
            String userInput = "event project meeting /from 6pm to 7pm";
            String taskDetails = getTaskDetails(userInput, getCommand(userInput));
            getTaskDuration(taskDetails);
        } catch (MissingTaskNameException e) {
            assertEquals(" oops! task name cannot be empty! "
                    + "please enter a task name after the type of task. for example:\n"
                    + "      todo cs2103t quiz", e.getMessage());
        } catch (MissingEventDurationException e) {
            assertEquals(" oops! event duration cannot be empty! "
                    + "please enter a duration after the task type and task name."
                    + " for example:\n      event cs2101 project meeting /from 4pm /to 7pm", e.getMessage());
        }
    }
}
