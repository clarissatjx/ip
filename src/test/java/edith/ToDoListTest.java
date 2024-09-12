package edith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import edith.task.ToDoList;
import org.junit.jupiter.api.Test;

import edith.task.exception.InvalidTaskNumberException;
import edith.task.type.ToDoTask;

public class ToDoListTest {

    @Test
    public void testAddTask() {
        ToDoTask task = new ToDoTask("read book");
        ToDoList list = new ToDoList();
        list.add(task);
        assertEquals(" 1.[T][ ] read book\n", list.toString());
    }

    @Test
    public void testDeleteTask() {
        ToDoTask task = new ToDoTask("read book");
        ToDoTask task1 = new ToDoTask("watch lecture");
        ToDoList list = new ToDoList();
        list.add(task);
        list.add(task1);
        try {
            list.delete(1);
            assertEquals(" 1.[T][ ] watch lecture\n", list.toString());
        } catch (InvalidTaskNumberException e) {
            fail();
        }
    }

}
