package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * View class. This class creates and manages the view.
 */
public class View {
    private final Text outputText;
    private final TextField textInputField;
    private Scene scene;

    /**
     * Constructor
     */
    public View(Controller controller) {
        // create a panel for our button, text fields, and texts
        VBox panel = new VBox();
        panel.setAlignment(Pos.CENTER);

        // read only text field
        Text sceneTitle = new Text("Zipcode Search");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        panel.getChildren().add(sceneTitle);

        // input text field
        textInputField = new TextField();
        textInputField.setText("");
        textInputField.setMaxWidth(100);
        textInputField.setOnAction(controller);
        panel.getChildren().add(textInputField);

        // search button
        Button button = new Button();
        button.setText("Search");
        button.setOnAction(controller);
        panel.getChildren().add(button);

        // text field to display the search results
        outputText = new Text();
        outputText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        panel.getChildren().add(outputText);

        scene = new Scene(panel, 300, 250);
    }

    /**
     * Get the scene for this view
     *
     * @return scene for this view
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Get the output text property
     *
     * @return output text property
     */
    public Text getOutputTextField() {
        return outputText;
    }

    /**
     * Get the input text property
     *
     * @return input text property
     */
    public TextField getInputTextField() {
        return textInputField;
    }
}
