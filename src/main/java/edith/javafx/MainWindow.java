package edith.javafx;

import edith.Edith;
import edith.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Edith edith;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nerd.JPG"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/spikeskin.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the edith.javafx.Duke instance */
    public void setEdith(Edith e) {
        edith = e;
        e.loadData();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.greetUser(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing edith.javafx.Duke's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = edith.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
