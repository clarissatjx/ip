package edith.exception;

public class MissingTaskNumberException extends Exception {
    public MissingTaskNumberException() {
        super(" which task would you like to mark/unmark/delete? Please specify with the task number. " +
                "for example:\n\n" +
                "      mark 3");
    }
}
